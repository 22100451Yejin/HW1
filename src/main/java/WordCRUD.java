import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;
    String Filename="Dictionary.txt" ;

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

    public void listAll(int Inputlevel){
        int j = 0;
        System.out.println("--------------------------------");
        for (int i = 0; i < list.size(); i++) {
            int level = list.get(i).getLevel();
            if (level==Inputlevel) {
                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                j++;
            }
        }
        System.out.println("--------------------------------");
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

    public void loadFile(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(Filename));
            String line;
            int count=0;

            while (true){
                line=br.readLine();
                if(line==null) break;
                String data[]=line.split(("\\|"));

                int level = Integer.parseInt(data[0]);
                String word=data[1];
                String meaning = data[2];
                list.add(new Word(0,level,word,meaning));
                count++;
            }
            br.close();
            System.out.println("==> "+count+"개 로딩 완료!!");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveFile(){
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(Filename));
            for(Word one : list){
                pr.write(one.toFIleString()+"\n");
            }
            pr.close();
            System.out.println("모든 단어 파일 저장 완료!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SearchLevel(){
        System.out.print("=> 레벨(1:초급, 2:중급, 3:고급) 선택 :");
        int level=s.nextInt();
        listAll(level);
    }

    public void SearchWord(){
        System.out.print("=> 검색할 단어 입력 : ");
        String word=s.next();
        listAll(word);
    }
}
