module.exports = {
    idleElevators: [],
    requestMap: {"up": [], "down": []},
    lastSelected: 'up',
    binaryInsert: (arr, ele, isReverse) => {
        let insertIndex = _.sortedIndex(arr, ele, i => isReverse ? -i : i);
        if (arr.length > insertIndex && arr[insertIndex] === ele) {
            return false;
        }
        arr.splice(insertIndex, 0, ele);
        return true;
    },
    idle: (elevator) => {
        if (elevator.getPressedFloors().length > 0) {
            let sortedPressedFloors = elevator.getPressedFloors();
            sortedPressedFloors.sort();
            let destFloor = elevator.lastSelected === 'up' ? sortedPressedFloors[0] : sortedPressedFloors[sortedPressedFloors.length] - 1;
            elevator.lastSelected = elevator.lastSelected === 'up' ? 'down' : 'up';
            let index = _.sortedIndex(sortedPressedFloors, elevator.currentFloor());
            if (elevator.currentFloor() > destFloor)
                elevator.destinationQueue = sortedPressedFloors.slice(index);
            else
                elevator.destinationQueue = sortedPressedFloors.slice(0, index).reverse();
            elevator.checkDestinationQueue();
        } else {
            if (this.lastSelected === 'up') {
                this.lastSelected = 'down';
                if (this.requestMap['down'].length > 0) {
                    let arr = this.requestMap['down'];
                    elevator.goToFloor(arr[arr.length - 1]);
                    arr.splice(arr.length - 1, 1);
                } else {
                    this.idleElevators.push(elevator);
                }
            } else {
                this.lastSelected = 'up';
                if (this.requestMap['up'].length > 0) {
                    let arr = this.requestMap['up'];
                    elevator.goToFloor(arr[0]);
                    arr.splice(0, 1);
                } else {
                    this.idleElevators.push(elevator);
                }
            }
        }
    },
    floorButtonPressed: (elevator, floor) => {
        let shouldInsert = (elevator.destinationDirection() === 'up' && floor > elevator.currentFloor())
            || (elevator.destinationDirection() === 'down' && floor < elevator.currentFloor())
            || elevator.destinationDirection() === 'stopped';
        if (shouldInsert && this.binaryInsert(elevator.destinationQueue, floor, elevator.destinationDirection() === 'down'))
            elevator.checkDestinationQueue();
    },
    passingFloor: (elevator, floor, dir) => {
        let searchIndex = _.indexOf(this.requestMap[dir], floor, true);
        if (searchIndex !== -1) {
            this.binaryInsert(elevator.destinationQueue, floor, elevator.destinationDirection() === 'down');
            elevator.checkDestinationQueue();
            this.requestMap[dir].splice(searchIndex, 1);
        }
    },
    stoppedAtFloor: (elevator, floor) => {
        let direction = elevator.destinationDirection();
        if (direction === 'down' || direction === 'up') {
            let searchIndex = _.indexOf(this.requestMap[direction], floor, true);
            if (searchIndex !== -1) {
                this.requestMap[direction].splice(searchIndex, 1);
            }
        }
    },
    buttonPressed: (floor, dir) => {
        let floorNum = floor.floorNum(), nearestIdle = null, elevatorIndex = -1, minDistance = Number.MAX_SAFE_INTEGER;
        this.idleElevators.forEach((elevator, index) => {
            let distance = Math.abs(floorNum - elevator.currentFloor());
            if (distance < minDistance) {
                nearestIdle = elevator;
                elevatorIndex = index;
                minDistance = distance;
            }
        });
        if (this.idleElevators.length !== 0) {
            this.idleElevators.splice(elevatorIndex, 1);
            nearestIdle.goToFloor(floorNum);
        } else
            this.binaryInsert(this.requestMap[dir], floorNum, false);
    },
    init: (elevators, floors) => {
        this.elevators = elevators;
        elevators.forEach((elevator, index) => {
            elevator.index = index;
            elevator.lastSelected = 'up';
            elevator.on("idle", () => this.idle(elevator));
            elevator.on("floor_button_pressed", (floor) => this.floorButtonPressed(elevator, floor));
            elevator.on("passing_floor", (floor, dir) => this.passingFloor(elevator, floor, dir));
            elevator.on("stopped_at_floor", (floor) => this.stoppedAtFloor(elevator, floor));
        });
        floors.forEach((floor) => {
            floor.on('up_button_pressed', () => this.buttonPressed(floor, "up"));
            floor.on('down_button_pressed', () => this.buttonPressed(floor, "down"));
        });
    },
    update: (dt, elevators, floors) => {
    }
};
