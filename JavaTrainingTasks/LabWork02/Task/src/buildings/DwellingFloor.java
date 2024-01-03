package buildings;

public class DwellingFloor {
    private int flatCount;                          // number of flats on the floor array
    private Flat[] flatArr;               // array of flats on the floor
    private int[] reservedFlatNumFloor = new int[0];    // array of reserved numbers of flats on the floor
    private Flat[] reservedFlatFloor = new Flat[0];     // array of reserved flats on the floor


    public DwellingFloor(int flatCount){                // create an array with default flats. Consist "flatCount" number of elements
        if (flatCount < 0){
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0");
        }
        this.flatArr = new Flat[flatCount];
        this.flatCount = flatCount;
        for(int i = 0; i < flatCount; i++){
            flatArr[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flatArr){               // create an array of flats identical to the parameter array of flats
        for (Flat tempFlat : flatArr){
            if (tempFlat == null){
                throw new IllegalArgumentException("Flat can't to be \"null\"");
            }
        }
        this.flatArr = flatArr;
        this.flatCount = flatArr.length;
    }

    public int getFlatCount() {
        return flatCount;
    }

    public double floorSquareSum(){
        double sum = 0;
        for(int i = 0; i < flatCount; i++){
            sum += flatArr[i].getFlatSquare();
        }
        return sum;
    }

    public double floorFlatSum(){
        double sum = 0;
        for(int i = 0; i < flatCount; i++){
            sum += flatArr[i].getRoomCount();
        }
        return sum;
    }

    public Flat[] getFlatArr() {
        return flatArr;
    }

    public Flat getFlatByNumber(int flatNum){
        if (flatNum < 0 || flatNum >= flatCount) {
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0 and < than number of flats on the floor");
        }
        return flatArr[flatNum];
    }

    public void setFlatByNumber(int flatNum, Flat flatReplace){
        if (flatNum < 0 || flatNum >= flatCount) {
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0 and < than number of flats on the floor");
        }
        if (flatReplace == null){
            throw new IllegalArgumentException("Flat can't to be \"null\"");
        }
        flatArr[flatNum] = flatReplace;
    }

    public void addFlatOnFloor(int flatNum, Flat flatAdd){
        if (flatNum < 0){
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0");
        }
        if (flatNum < flatCount) {
            throw new IllegalArgumentException("Number of flat is incorrect. Flat with this number on the floor exists now");
        }
        for (int num : reservedFlatNumFloor) {
            if (num == flatNum){
                throw new IllegalArgumentException("Number of flat is incorrect. Flat with this number reserved before on the floor");
            }
        }
        if (flatAdd == null){
            throw new IllegalArgumentException("Flat can't to be \"null\"");
        }

        // add new flat and its number to the floor reserve arrays
        int reservedArrSize = reservedFlatNumFloor.length;
        int[] tempIntArr = new int[reservedArrSize+1];   // create arrays to copy data from original arrays and add 1 new element
        Flat[] tempFlatArr = new Flat[reservedArrSize+1];

        for (int i = 0; i < reservedArrSize; i++){       // copy elements from original arr to new with bigger size
            tempIntArr[i] = reservedFlatNumFloor[i];
            tempFlatArr[i] = reservedFlatFloor[i];
        }
        tempIntArr[reservedArrSize] = flatNum;           // add number of future flat into bigger reserved array
        tempFlatArr[reservedArrSize] = flatAdd;
        reservedFlatNumFloor = tempIntArr;               // reinitialize arrays with future number of flat
        reservedFlatFloor = tempFlatArr;
        reservedArrSize++;

        boolean needToFind = true;
        while (needToFind){                                 // try to find suitable number of flat in array of reserved numbers
            int foundIndex = -1;
            for (int i = 0; i < reservedArrSize; i++){
                if (reservedFlatNumFloor[i] == flatCount){  // the next suitable number for array of flats
                    foundIndex = i;                         // is equal to the size of that array
                    break;
                }
            }
            if (foundIndex == -1){                          // if we didn't find suitable number in reserve,
                needToFind = false;                         // there is no reason to find it again
            }
            else {
                tempFlatArr = new Flat[flatCount+1];        // if we found suitable number in reserve,
                for (int i = 0; i < flatCount; i++){        // we add flat with that number to the main array
                    tempFlatArr[i] = flatArr[i];
                }
                tempFlatArr[flatCount] = reservedFlatFloor[foundIndex];
                flatArr = tempFlatArr;
                flatCount++;                                // after adding we need to increase number of flats on the floor

                tempIntArr = new int[reservedArrSize - 1];      // deleting found number and flat from reserved arrays
                tempFlatArr = new Flat[reservedArrSize - 1];
                int tempIndex = 0;                              // tempIndex is a way to avoid out of bounds exception
                for (int i = 0; i < reservedArrSize; i++){      // in smaller array tempIntArr
                    if (i == foundIndex){
                        continue;
                    }
                    tempIntArr[tempIndex] = reservedFlatNumFloor[i];
                    tempFlatArr[tempIndex] = reservedFlatFloor[i];
                    tempIndex++;
                }
                reservedFlatNumFloor = tempIntArr;
                reservedFlatFloor = tempFlatArr;
                reservedArrSize--;
                System.out.println("The flat \"" + flatArr[flatCount-1] + "\" with number " + (flatCount-1) + " successfully added to the floor");
            }
        }
    }

    public void delFlatOnFloor(int flatNum){
        if (flatNum < 0){
            throw new IllegalArgumentException("Number of flat is incorrect. It has to be >= 0");
        }
        if (flatNum >= flatCount) {
            throw new IllegalArgumentException("Number of flat is incorrect. Flat with this number doesn't exist on the floor");
        }

        Flat[] tempFlatArr = new Flat[flatCount - 1];
        int tempIndex = 0;                                  // tempIndex is way to avoid out of bounds exception
        for (int i = 0; i < flatCount; i++){                // in smaller array tempFlatArr
            if (i == flatNum){
                continue;                                   // copy of array of flat on the floor without flat that want to delete
            }
            tempFlatArr[tempIndex] = flatArr[i];
            tempIndex++;
        }
        flatArr = tempFlatArr;
        flatCount--;
    }

    public double getBestSpace(){
        double maxSquare = 0;
        for (Flat tempFlat : flatArr) {
            if(tempFlat.getFlatSquare() > maxSquare){
                maxSquare = tempFlat.getFlatSquare();
            }
        }
        return  maxSquare;
    }

}