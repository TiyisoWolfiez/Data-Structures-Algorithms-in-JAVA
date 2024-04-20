public class RecursiveArray {
    public Integer[] array;

    public RecursiveArray() {
        this.array = new Integer[0];
    }

    public RecursiveArray(String input) {
        
        if(input.isEmpty()) {
            this.array = new Integer[0];
        } else {
            String[] inputParts = input.split(",");

            this.array = new Integer[inputParts.length];
            initialiseArrayHelper(inputParts, 0);
        }
    }

    @Override
    public String toString() {
        if(this.array.length == 0){
            return "Empty Array";
        } else {
            return toStringHelper(0);
        }
    }

    public void append(Integer value) {
        int k = this.array.length;
        Integer[] newArr = new Integer[k+1];
        copyArrAppend(this.array, newArr, 0);
        newArr[k] = value;
        this.array = newArr;
    }

    public void prepend(Integer value) {
        int k = this.array.length;
        Integer[] newArr = new Integer[k+1];
        copyArrPrepend(this.array, newArr, 0, 1);
        newArr[0] = value;
        this.array = newArr;
    }

    public boolean contains(Integer value) {
        int k = 0;
        return containedHelper(this.array, value, k);
    }

    public boolean isAscending() {
        int k = 0;
        return isAscendingHelper(this.array, k);
    }

    public boolean isDescending() {
        int k = 0;
        return isDescendingHelper(this.array,k);
    }

    public void sortAscending() {
        sortAscendingHelper(this.array, 0);
    }

    public void sortDescending() {
        sortDescendingHelper(this.array, 0);
    }

    private void initialiseArrayHelper(String[] inputParts, int k) {
        if(k < inputParts.length) {
            this.array[k] = Integer.parseInt(inputParts[k]);
            initialiseArrayHelper(inputParts, ++k);
        }
    }

    private String toStringHelper(int k){
        int ArrLen = this.array.length;
        if(k >= ArrLen)
            return "]"; 
        if(k == 0)
            return "[" + array[k] + toStringHelper(++k);
        
        return "," + array[k] + toStringHelper(++k);
    }

    private void copyArrAppend(Integer[] src, Integer[] dst, int i){
        int srcLen = src.length;

        if(i < srcLen) {
            dst[i] = src[i];
            copyArrAppend(src, dst, ++i);
        } 
        else 
            {
                return;
            }
    }

    private void copyArrPrepend(Integer[] src, Integer[] dst, int s, int d) {
        int srcLen = src.length;

        if(s < srcLen) {
            dst[d] = src[s];
            copyArrPrepend(src, dst, ++s, ++d);
        }
        else {
            return;
        }
    }

    private boolean containedHelper(Integer[] arr, Integer value, int i) {
        int srcLen = arr.length;

        if(i >= srcLen) return false;
        if(arr[i].equals(value)) return true;
        return containedHelper(arr, value, ++i);
    }

    private boolean isAscendingHelper(Integer[] arr, int i){
        int srcLen = arr.length;

        if(i >= srcLen - 1) return true;
        if(arr[i] > arr[i + 1]) return false;
        return isAscendingHelper(arr,++i);
    }

    private boolean isDescendingHelper(Integer[] arr, int k){
        int srcLen = arr.length;

        if(k >= srcLen - 1) return true;
        if(arr[k] < arr[k + 1]) return false;
        return isDescendingHelper(arr,++k);
    }

    private void sortAscendingHelper(Integer[] arr, int k) {
        int srcLen = arr.length - 1;
        if( k >= srcLen) return;

        int min = findMin(arr, k , k , arr[k]);

        if(min != k) {
            int temp = arr[k];
            arr[k] = arr[min];
            arr[min] = temp;
        }
        sortAscendingHelper(arr, ++k);
    }

    private int findMin(Integer[] arr, int s, int minIndex, int min) {
        if(s >= arr.length) return minIndex;

        if(arr[s] < min) {
            min = arr[s];
            minIndex = s;
        }
        return findMin(arr, ++s, minIndex, min);
    }

    private void sortDescendingHelper(Integer[] arr, int k) {
        int srcLen = arr.length - 1;
        if( k >= srcLen) return;

        int max = findMax(arr, k, k, arr[k]);
      
        if(max != k){
            int temp = arr[k];
            arr[k] = arr[max];
            arr[max] = temp;
        }
        sortDescendingHelper(arr, ++k);
    }
    private int findMax(Integer[] arr, int s, int maxIndex, int max) {
        int srcLen = arr.length;
        if(s >= srcLen)
            return maxIndex;
        
        if(arr[s] > max) {
            max = arr[s];
            maxIndex = s;
        }
        return findMax(arr, ++s, maxIndex, max);
    }
}

