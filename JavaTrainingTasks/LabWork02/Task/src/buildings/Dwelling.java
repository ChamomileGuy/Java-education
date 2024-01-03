package buildings;

public class Dwelling {
    private final int floorCount;                 // number of floors in the Dwelling
    private final DwellingFloor[] floorArr;      // array of floors in the Dwelling
    private int[] reservedFlatNumDwell = new int[0];           // array of reserved numbers of flats in the Dwelling
    private Flat[] reservedFlatDwell = new Flat[0];            // array of reserved flats in the Dwelling

    public Dwelling(int floorCount, int[] arrFlatCount) {
        if (floorCount < 0) {
            throw new IllegalArgumentException("Number of floor is incorrect. It has to be >= 0.");
        }
        if (floorCount < arrFlatCount.length) {
            throw new IllegalArgumentException("Count of floors is too small. It has to be >= length of array with count of flats on the floors.");
        }
        this.floorArr = new DwellingFloor[floorCount];
        this.floorCount = floorCount;

        for (int i = 0; i < floorCount; i++) {                   // create array of floors in "floorArr".
            if (i >= arrFlatCount.length) {                      // if amount of floors from "floorCount" is bigger than amount of floors from array "arrFlatCount",
                floorArr[i] = new DwellingFloor(0);    // then create an empty floors in array with floors "floorArr"
                continue;
            }
            if (arrFlatCount[i] < 0) {
                throw new IllegalArgumentException("Count of flats in array is incorrect. It has to be >= 0.");
            }
            floorArr[i] = new DwellingFloor(arrFlatCount[i]);
        }
    }

    public Dwelling(DwellingFloor[] floorArr) {
        for (DwellingFloor tempFloor : floorArr) {
            if (tempFloor == null) {
                throw new IllegalArgumentException("Floor can't to be \"null\".");
            }
        }
        this.floorArr = floorArr;
        this.floorCount = floorArr.length;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public int flatCountDwell() {                     // total count of flats in the dwelling
        int sum = 0;
        for (DwellingFloor tempFloor : floorArr) {
            sum += tempFloor.getFlatCount();
        }
        return sum;
    }

    public double dwellSquareSum() {                      // total flats area in the dwelling
        double sum = 0;
        for (DwellingFloor tempFloor : floorArr) {
            sum += tempFloor.floorSquareSum();
        }
        return sum;
    }

    public int dwellRoomSum() {                                          // total number of rooms in the dwelling
        int sum = 0;
        for (DwellingFloor tempFloor : floorArr) {                       // go to each floor
            for (int i = 0; i < tempFloor.getFlatCount(); i++) {
                sum += tempFloor.getFlatByNumber(i).getRoomCount();     // go to each flat and get count of room of flat
            }
        }
        return sum;
    }

    public DwellingFloor[] getFloorArr() {
        return floorArr;
    }

    public DwellingFloor floorByNumber(int floorNum) {
        if (floorNum < 0 || floorNum >= floorCount) {
            throw new IllegalArgumentException("Number of floor is incorrect. It has to be >= 0 and < than number of floors in the dwelling.");
        }
        return floorArr[floorNum];
    }

    public void setFloorByNum(int floorNum, DwellingFloor floorReplace) {
        if (floorNum < 0 || floorNum >= floorCount) {
            throw new IllegalArgumentException("Number of floor is incorrect. It has to be >= 0 and < than number of floors in the dwelling.");
        }
        if (floorReplace == null) {
            throw new IllegalArgumentException("Floor can't to be \"null\".");
        }
        floorArr[floorNum] = floorReplace;
    }

    public Flat getFlatInDwell(int flatNum) {             // get flat by number of flat at the dwelling
        if (flatNum < 0 || flatNum >= this.flatCountDwell()) {
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0 and < than number of flats in the dwelling.");
        }

        /*
        If the count of flats on the floor is less than or equal to the number of the flat,
        that we are looking for, then we subtract from the flat number the count
        of flats on this floor, and go back to the beginning of the cycle.

        If the count of flats on the floor is greater than the number of the flat,
        we return flat with that number on the floor
         */
        int flatCountFloor;
        Flat foundFlat = null;
        for (DwellingFloor tempFloor : floorArr) {
            flatCountFloor = tempFloor.getFlatCount();
            if (flatCountFloor <= flatNum) {
                flatNum -= flatCountFloor;
                continue;
            }
            foundFlat = tempFloor.getFlatByNumber(flatNum);
            break;
        }

        return foundFlat;
    }

    public void setFlatInDwell(int flatNum, Flat flatReplace) {
        if (flatNum < 0 || flatNum >= this.flatCountDwell()) {
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0 and < than number of flats in the dwelling.");
        }
        if (flatReplace == null) {
            throw new IllegalArgumentException("Floor can't to be \"null\".");
        }

        /*
        If the count of flats on the floor is less than or equal to the number of the flat,
        that we are looking for, then we subtract from the flat number the count
        of flats on this floor, and go back to the beginning of the cycle.

        If the count of flats on the floor is greater than the number of the flat,
        we replace flat with that number on the floor to the new flat
         */
        int flatCountFloor;
        for (DwellingFloor tempFloor : floorArr) {
            flatCountFloor = tempFloor.getFlatCount();
            if (flatCountFloor <= flatNum) {
                flatNum -= flatCountFloor;
                continue;
            }
            tempFloor.setFlatByNumber(flatNum, flatReplace);
            break;
        }
    }

    public void addFlatInDwell(int flatNum, Flat flatAdd) {
        int flatCountDwell = this.flatCountDwell();             // total count of flats in the dwelling
        boolean needToReserve = true;                           // flag if we can to reserve the flat with the number from parameters
        int reservedArrSize = reservedFlatNumDwell.length;      // count of reserved numbers of flats in reserve arrays
        int[] tempIntArr;
        Flat[] tempFlatArr;

        if (flatAdd == null) {
            System.out.println("Flat in parameter can't to be \"null\".");
            System.out.println("This method will not be able to add this flat, ");
            System.out.println("but we will try to put other flats with reserved numbers into the dwelling.");
            needToReserve = false;
        }
        if (flatNum < flatCountDwell) {
            System.out.println("Number of flat in parameter is incorrect. It has to be >= than number of flats in the dwelling.");
            System.out.println("This method will not be able to add flat with number " + flatNum + ", ");
            System.out.println("but this method will try to put flats with reserved numbers into the dwelling.");
            needToReserve = false;
        }
        for (int num : reservedFlatNumDwell) {
            if (num == flatNum) {
                System.out.println("Number of flat in parameter is incorrect. Flat with this number reserved before in the dwelling.");
                System.out.println("This method will not be able to add another flat with number " + flatNum + ", ");
                System.out.println("but this method will try to put flats with reserved numbers into the dwelling.");
                needToReserve = false;
            }
        }

        // add new flat and its number to the dwelling reserve arrays
        if (needToReserve) {
            tempIntArr = new int[reservedArrSize + 1];   // create arrays to copy data from original arrays and add 1 new element
            tempFlatArr = new Flat[reservedArrSize + 1];

            for (int i = 0; i < reservedArrSize; i++) {       // copy elements from original arr to the new with bigger size
                tempIntArr[i] = reservedFlatNumDwell[i];
                tempFlatArr[i] = reservedFlatDwell[i];
            }
            tempIntArr[reservedArrSize] = flatNum;           // add number of the future flat into bigger reserved array
            tempFlatArr[reservedArrSize] = flatAdd;
            reservedFlatNumDwell = tempIntArr;            // reinitialize arrays with future number of flat
            reservedFlatDwell = tempFlatArr;
            reservedArrSize++;
        }

        boolean needToFind = true;
        while (needToFind) {
            int foundIndex = -1;
            int minNumReserved = Integer.MAX_VALUE;
            for (int i = 0; i < reservedArrSize; i++) {
                if (reservedFlatNumDwell[i] < minNumReserved) {  // the next possible number of flat in the dwelling
                    minNumReserved = reservedFlatNumDwell[i];   // is the minimal number in array of reserved numbers of flats
                    foundIndex = i;
                }
            }
            if (foundIndex == -1) {                          // if we didn't find suitable number in reserve,
                needToFind = false;                         // there is no reason to find it again
                continue;
            }

            /*
            During work of addFlatOnFloor method there is might to be situation
            when amount of flats on the floor was added, and total count of
            flats in the dwelling became more than minimal number of flat in array for
            reserved flats in the dwelling. Then we need to delete this flat from reserve
            and start to find another minimal number of flat in reserve for dwelling
             */
            if (minNumReserved < flatCountDwell) {
                System.out.println("Flat with number " + minNumReserved + " created earlier in the dwelling, therefore, ");
                System.out.println("the flat with number " + minNumReserved + " will not be able to create in the dwelling.");
                System.out.println("This number of flat deleted from reserved for the dwelling.");
            }

            /*
            We need to define which number will have flat with number minNumReserved at the last floor
            Formula for calculation: (desired flat number, converted for the top floor) = (desired flat number) - ((total count of flats in the dwelling) - (number of flats on the top floor))
            Then if we can add flat with found number to the top floor, we call method addFlatOnFloor for the top floor.
            Else (if found number != count of flat at the top floor), we can't add anything to the dwelling, and go exit from the method
             */
            else {
                int flatNumTopFloor = reservedFlatNumDwell[foundIndex] - (flatCountDwell - this.floorByNumber(floorCount - 1).getFlatCount());
                if (flatNumTopFloor == this.floorByNumber(floorCount - 1).getFlatCount()) {
                    this.floorByNumber(floorCount - 1).addFlatOnFloor(flatNumTopFloor, reservedFlatDwell[foundIndex]);
                    flatCountDwell = this.flatCountDwell();
                    System.out.println("The flat \"" + reservedFlatDwell[foundIndex] + "\" with number " + reservedFlatNumDwell[foundIndex] + " successfully added into the dwelling");
                } else {
                    needToFind = false;
                    continue;
                }
            }

            tempIntArr = new int[reservedArrSize - 1];      // deleting found number and flat from reserved arrays
            tempFlatArr = new Flat[reservedArrSize - 1];
            int tempIndex = 0;                              // tempIndex is a way to avoid out of bounds exception
            for (int i = 0; i < reservedArrSize; i++) {      // in smaller array tempIntArr
                if (i == foundIndex) {
                    continue;
                }
                tempIntArr[tempIndex] = reservedFlatNumDwell[i];
                tempFlatArr[tempIndex] = reservedFlatDwell[i];
                tempIndex++;
            }
            reservedFlatNumDwell = tempIntArr;
            reservedFlatDwell = tempFlatArr;
            reservedArrSize--;
        }
    }

    public void delFlatInDwel(int flatNum) {
        if (flatNum < 0 || flatNum >= this.flatCountDwell()) {
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0 and < than number of flats in the dwelling.");
        }

        /*
        find suitable floor, calculate which number will have this flat at the suitable floor,
        call the method delFlatOnFloor with calculated number
         */
        int flatCountFloor;
        for (DwellingFloor tempFloor : floorArr) {
            flatCountFloor = tempFloor.getFlatCount();
            if (flatCountFloor <= flatNum) {
                flatNum -= flatCountFloor;
                continue;
            }
            tempFloor.delFlatOnFloor(flatNum);
            break;
        }
    }

    public double getBestSpace(){
        double maxSquare = 0;
        for (DwellingFloor tempFloor : floorArr) {
            if(tempFloor.getBestSpace() > maxSquare){
                maxSquare = tempFloor.getBestSpace();
            }
        }
        return  maxSquare;
    }

    public Flat[] sortedBySquareFlats(){
        Flat[] resArray = new Flat[this.flatCountDwell()];
        int counter = 0;
        for (DwellingFloor tempFloor : floorArr){           // filling an array with flats
            int flatCount = tempFloor.getFlatCount();
            Flat[] tempFlatArr = tempFloor.getFlatArr();
            for (int j = 0; j < flatCount; j++){
                resArray[counter] = tempFlatArr[j];
                counter++;
            }
        }
        quickSortFlats(resArray, 0, resArray.length - 1);
        return resArray;
    }

    public static void quickSortFlats(Flat[] sortArr, int low, int high) {
        // end of array is empty or there is nothing to do with array
        if (sortArr.length == 0 || low >= high) return;

        // select pivot
        int middle = low + (high - low) / 2;
        double border = sortArr[middle].getFlatSquare();

        // elements < than pivot go to the right, elements > than pivot go the left part of array
        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i].getFlatSquare() > border) i++;
            while (sortArr[j].getFlatSquare() < border) j--;
            if (i <= j) {
                Flat swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }

        // sort elements in left and right parts
        if (low < j) quickSortFlats(sortArr, low, j);
        if (high > i) quickSortFlats(sortArr, i, high);
    }
}
/*



 */