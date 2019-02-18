module.exports = {
    idleElevators: [],
    requestMap: {"up": [], "down": []},
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
            // let destFloor = Math.random() >= 0.5 ? sortedPressedFloors[0] : sortedPressedFloors[sortedPressedFloors.length] - 1;
            let destFloor = Math.abs(elevator.currentFloor() - sortedPressedFloors[0]) <
            Math.abs(elevator.currentFloor() - sortedPressedFloors[sortedPressedFloors.length - 1]) ?
                sortedPressedFloors[0] : sortedPressedFloors[sortedPressedFloors.length - 1];
            let index = _.sortedIndex(sortedPressedFloors, elevator.currentFloor());
            if (elevator.currentFloor() > destFloor)
                elevator.destinationQueue = sortedPressedFloors.slice(index);
            else
                elevator.destinationQueue = sortedPressedFloors.slice(0, index).reverse();
            elevator.checkDestinationQueue();
        } else
            this.idleElevators.push(elevator);
    },
    floorButtonPressed: (elevator, index, floor) => {
        let shouldInsert = (elevator.destinationDirection() === 'up' && floor > elevator.currentFloor())
            || (elevator.destinationDirection() === 'down' && floor < elevator.currentFloor())
            || elevator.destinationDirection() === 'stopped';
        if (shouldInsert && this.binaryInsert(elevator.destinationQueue, floor, elevator.destinationDirection() === 'down'))
            elevator.checkDestinationQueue();
    },
    passingFloor: (elevator, index, floor, dir) => {
        let searchIndex = _.indexOf(this.requestMap[dir], floor, true);
        if (searchIndex !== -1) {
            this.binaryInsert(elevator.destinationQueue, floor, elevator.destinationDirection() === 'down');
            elevator.checkDestinationQueue();
            this.requestMap[dir].splice(searchIndex, 1);
        }
    },
    stoppedAtFloor: (elevator, index, floor) => {
        let direction = elevator.destinationDirection();
        if (direction === 'down' || direction === 'up') {
            let searchIndex = _.indexOf(this.requestMap[direction], floor, true);
            if (searchIndex !== -1) {
                this.requestMap[direction].splice(searchIndex, 1);
            }
        }
    },
    buttonPressed: (floor, dir) => {
        let floorNum = floor.floorNum();
        if (this.idleElevators.length !== 0) {
            let idleElevator = this.idleElevators.shift();
            idleElevator.goToFloor(floorNum);
        } else
            this.binaryInsert(this.requestMap[dir], floorNum, false);
    },
    init: (elevators, floors) => {
        this.elevators = elevators;
        elevators.forEach((elevator, index) => {
            elevator.on("idle", () => this.idle(elevator, index));
            elevator.on("floor_button_pressed", (floor) => this.floorButtonPressed(elevator, index, floor));
            elevator.on("passing_floor", (floor, dir) => this.passingFloor(elevator, index, floor, dir));
            elevator.on("stopped_at_floor", (floor) => this.stoppedAtFloor(elevator, index, floor));
        });
        floors.forEach((floor) => {
            floor.on('up_button_pressed', () => this.buttonPressed(floor, "up"));
            floor.on('down_button_pressed', () => this.buttonPressed(floor, "down"));
        });
    },
    update: (dt, elevators, floors) => {
    }
};
