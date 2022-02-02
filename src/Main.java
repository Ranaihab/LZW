//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2


public class Main {
    public static void main(String[] args) {
        LZW lzw = new LZW();

        lzw.compress();
        System.out.println();
        System.out.print("Text after Decompression: ");
        lzw.decompress();

    }
}
