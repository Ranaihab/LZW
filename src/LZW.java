//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

import java.util.ArrayList;
import java.util.Scanner;

public class LZW {
    private ArrayList<Tag> tags;

    public LZW(){
        tags = new ArrayList<>();
    }

    private void addTags(int pos, ArrayList<Integer> p){
        ArrayList<Integer> position = new ArrayList<>();
        while(pos != 0 ){
            position.add(pos%2);
            pos = pos>>> 1;
        }

        for(int i =0; i<p.size(); i++){
            Tag tag=new Tag(position.size());
            tag.setPosition(p.get(i));
            tags.add(tag);
        }

        System.out.println("Tags:");
        for(int i = 0; i<tags.size();i++){
            System.out.println(tags.get(i));
        }
    }

    public void compress(){
        ArrayList<String> dictionary = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            dictionary.add(""+(char) i);
        }

        Scanner in = new Scanner(System.in);
        System.out.print("Text to compress: ");
        String text =  in.nextLine();

        ArrayList<Integer> position = new ArrayList<>();
        for(int i =0; i<text.length(); i++){
            String searchText = text.substring(i, i+1);
            boolean isAdded = false;
            for(int j =i+1; j<text.length(); j++){
                isAdded = false;
                String newSearchText = searchText + text.charAt(j);
                if(!dictionary.contains(newSearchText)){
                    dictionary.add(newSearchText);
                    position.add(dictionary.indexOf(searchText));
                    i+=(searchText.length()-1);
                    isAdded = true;
                    break;
                }
                searchText = newSearchText;
            }
            if(!isAdded){
                position.add(dictionary.indexOf(searchText));
                i+=(searchText.length()-1);
            }
        }

        int maxP=0;
        for(int j =0;j<position.size();j++){
            if(maxP<position.get(j)){
                maxP= position.get(j);
            }
        }
        addTags(maxP, position);
    }

    public void decompress(){
        ArrayList<String> dictionary = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            dictionary.add("" + (char) i);
        }
        String text = "";
        text+=dictionary.get(tags.get(0).getPosition());

        for(int i = 1; i < tags.size(); i++){
            int pos = tags.get(i).getPosition();
            if(pos >= dictionary.size()){
                String symbol = dictionary.get(tags.get(i-1).getPosition());
                symbol += symbol.charAt(0);
                dictionary.add(symbol);
                text+=symbol;
                continue;
            }
            String symbol = dictionary.get(tags.get(i).getPosition());
            text+=symbol;
            dictionary.add(dictionary.get(tags.get(i-1).getPosition()) + symbol.charAt(0));
        }
        System.out.println(text);
    }
}