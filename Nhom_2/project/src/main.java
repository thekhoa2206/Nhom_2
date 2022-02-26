public class main {
    public static void main(String[] args) {
        String[] arr = {"cat", "fla", "gn", "country", "w3resource"};
        String temp = arr[0];
        int lengthMax = temp.length();
        for(int i = 0; i < arr.length - 1 ; i++ ){
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].length() < arr[j].length()) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    if( arr[i].length() > lengthMax){
                        lengthMax = arr[i].length();
                    }
                }

            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(lengthMax == arr[i].length()){
                System.out.print(arr[i] + " ");
            }
        }
    }
}