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
    elevators: [],
    getPrintableElevator: e => {
        return {
            "floor": e.currentFloor(),
            "goingUpIndicator": e.goingUpIndicator(),
            "goingDownIndicator": e.goingDownIndicator(),
            "pressedFloors": e.getPressedFloors(),
            "destinationQueue": e.destinationQueue,
            "destinationDirection": e.destinationDirection()
        }
    },
    logState: () => {
        console.log(JSON.stringify(this.elevators.map(this.getPrintableElevator)));
        console.log(JSON.stringify(this.requestMap));
    },
    idle: (elevator, index) => {
        console.log(`event=idle; index=${index}`);
        // elevator.goingDownIndicator(false);
        // elevator.goingUpIndicator(false);
        if (elevator.getPressedFloors().length > 0) {
            let sortedPressedFloors = elevator.getPressedFloors();
            sortedPressedFloors.sort();
            let destFloor = Math.abs(elevator.currentFloor() - sortedPressedFloors[0]) <
            Math.abs(elevator.currentFloor() - sortedPressedFloors[sortedPressedFloors.length - 1]) ?
                sortedPressedFloors[0] : sortedPressedFloors[sortedPressedFloors.length - 1];
            let index = _.sortedIndex(sortedPressedFloors, elevator.currentFloor());
            if (elevator.currentFloor() > destFloor) {
                elevator.destinationQueue = sortedPressedFloors.slice(index);
                // elevator.goingUpIndicator(true);
            } else {
                elevator.destinationQueue = sortedPressedFloors.slice(0, index).reverse();
                // elevator.goingDownIndicator(true);
            }
            elevator.checkDestinationQueue();
        } else {
            this.idleElevators.push(elevator);
        }
        this.logState();
    },
    floorButtonPressed: (elevator, index, floor) => {
        console.log(`event=elevator_button; index=${index}; floor=${floor}`);
        let shouldInsert = (elevator.destinationDirection() === 'up' && floor > elevator.currentFloor())
            || (elevator.destinationDirection() === 'down' && floor < elevator.currentFloor())
            || elevator.destinationDirection() === 'stopped';
        if (shouldInsert && this.binaryInsert(elevator.destinationQueue, floor, elevator.destinationDirection() === 'down')) {
            elevator.checkDestinationQueue();
            if (!elevator.goingUpIndicator() && !elevator.goingDownIndicator()) {
                if (elevator.destinationDirection() === 'down') ;
                // elevator.goingDownIndicator(true);
                else ;
                // elevator.goingUpIndicator(true);
            }
        }
        this.logState();
    },
    passingFloor: (elevator, index, floor, dir) => {
        console.log(`event=pass_floor; index=${index}; floor=${floor}; dir=${dir}`);
    },
    stoppedAtFloor: (elevator, index, floor) => {
        console.log(`event=stop_floor; index=${index}; floor=${floor}`);
        let direction = elevator.destinationDirection();
        if (direction === 'down' || direction === 'up') {
            let index = _.indexOf(this.requestMap[direction], floor, true);
            if (index !== -1) {
                this.requestMap[direction].splice(index, 1);
            }
        }
        this.logState();
    },
    buttonPressed: (floor, dir) => {
        let floorNum = floor.floorNum();
        console.log(`event=floor_button; floor=${floorNum}; dir=${dir}`);
        if (this.idleElevators.length !== 0) {
            let idleElevator = this.idleElevators.shift();
            idleElevator.goToFloor(floorNum);
            /*if (floorNum > idleElevator.currentFloor()) {
                idleElevator.goingUpIndicator(true);
            } else if (floorNum < idleElevator.currentFloor()) {
                idleElevator.goingDownIndicator(true);
            }*/
        } else
            this.binaryInsert(this.requestMap[dir], floorNum, false);
        this.logState();
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
