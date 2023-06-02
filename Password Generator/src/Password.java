public class Password {

    String Value;
    int Length;

    public Password(String s){
        Value = s;
        Length = s.length();
    }

    public int CharType(char c){
        int val;

        if((int) c >=65 && (int) c <=90)
            val = 1;

        else if((int) c >=97 && (int) c <=122)
            val = 2;

        else if((int) c >= 60 && (int) c <= 71)
            val = 3;

        else
            val =4;

        return val;
    }

    public int passwordStrength(){
        String s = this.Value;
        boolean usedUpper = false;
        boolean usedLower = false;
        boolean usedNumber = false;
        boolean usedSymbol = false;
        int type;
        int score = 0;

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            type = CharType(c);

            if(type == 1) usedUpper = true;
            if(type == 2) usedLower = true;
            if(type == 3) usedNumber = true;
            if(type == 4) usedSymbol = true;
        }

        if(usedUpper) score+=1;
        if(usedLower) score+=1;
        if(usedNumber) score+=1;
        if(usedSymbol) score+=1;

        if(s.length() >= 8) score+=1;
        if(s.length() >= 16) score+=2;

        return score;
    }

    public String calculateScore(){
        int score = this.passwordStrength();

        if(score == 6)
            return "This is a strong password :D Check the useful Information section to make sure it satisfies the guidelines";
        if(score >= 4)
            return "This is a good password :) but you can do still better";
        if(score == 3)
            return "This is a medium password :/ try making it better";
        else return "This is a weak password :( definitely find a new one";
    }

    @Override
    public String toString(){
        return Value;
    }
}
