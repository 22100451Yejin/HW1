import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;

    WordCRUD(Scanner s){
        list=new ArrayList<>();
        this.s=s;
    }
    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int lever=s.nextInt();
        String word=s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning=s.nextLine();

        return new Word(0,lever,word,meaning);
    }

    public void addWord(){
        Word one= (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다.");
    }

    public void listAll(){
        System.out.println("\n--------------------------------");
        for(int i=0;i<list.size();i++){
            System.out.print((i+1)+" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void update() {
        System.out.print("\n=> 수정할 단어 검색 : ");
        String UpdateWord=s.next();
        ArrayList<Integer> NewList = this.listAll(UpdateWord);
        System.out.print("=> 수정할 번호 선택 : ");
        int UpdateNum = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word =list.get(NewList.get(UpdateNum-1));
        word.setMeaning(meaning);
        System.out.println("\n단어 수정이 성공적으로 되었습니다!!");
    }

    public ArrayList<Integer> listAll(String UpdateWord) {
        ArrayList<Integer> NewList = new ArrayList<>();
        int j = 0;
        System.out.println("--------------------------------");
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if (word.contains(UpdateWord)) {
                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                NewList.add(i);
                j++;
            }
        }
        System.out.println("--------------------------------");
        return NewList;
    }

    @Override
    public void delete() {
        System.out.print("\n=> 삭제할 단어 검색 : ");
        String DeleteWord=s.next();
        ArrayList<Integer> NewList = this.listAll(DeleteWord);
        System.out.print("=> 삭제할 번호 선택 : ");
        int DeleteNum = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String Danswer = s.next();
        if(Danswer.equalsIgnoreCase("y")){
            list.remove(list.get(DeleteNum-1));
            System.out.println("\n선택한 단어 삭제 완료 !!!");
        }
        else
            System.out.println("취소되었습니다.");
    }

}
