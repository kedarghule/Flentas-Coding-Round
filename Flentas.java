import java.io.*;
class Flentas {

    static int primes[] = new int[26];  //a prime for each character
    public static void main(String args[]) throws Exception {
        FileWriter fw = new FileWriter("sample.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of records: ");
        int n = Integer.parseInt(br.readLine());
        if(n<1 || n>100) {
            System.out.println("1<=T<=100 not satisfied.");
            System.exit(0);
        }
        fw.write(Integer.toString(n)+"\n");
        int i;
        String s = new String();
        for(i=0;i<n;i++) {
            s = br.readLine();
            if(s.length()<1 || s.length()>1000) {
                System.out.println("Pattern length>1000 || Pattern length<1");
                System.exit(0);
            }
            fw.write(s+"\n");
            
            s = br.readLine();
            if(s.length()<1 || s.length()>100000) {
                System.out.println("Text length>100000 || Text length<1");
                System.exit(0);
            }
            fw.write(s+"\n");

        }
        fw.close();

        BufferedReader stdin = new BufferedReader(new FileReader("sample.txt"));
        n = Integer.parseInt(stdin.readLine()); //number of Test Cases
        System.out.println("\n\nTotal number of cases: "+n+"\n");
        String pattern = "";
        String text = "";
        int cnt=0;
        assign();
        while(((pattern=stdin.readLine())!=null && (text=stdin.readLine())!=null) && cnt<=n) {
            cnt++;
            System.out.print(pattern+" "+text);
            if(check_subs(pattern, text)) {
                System.out.println(" -> YES\n");
            } else {
                System.out.println(" -> NO\n");
            }
        }
    }

    public static void assign() {
        int i,j,flag=0,k=0;
        for(i=2;k<=25;i++) {
            flag=0;
            for(j=2;j<=Math.sqrt(i);j++) {
                if(i%j==0) {
                    flag=1;
                    break;
                }
            }
            if(flag==1) continue;
            else primes[k++] = i;
        }
/*        for(i=0;i<26;i++)
        System.out.println(primes[i]);  */
    }

    public static int find_product(String pattern) {
        int product=1;
        int i;
        for(i=0;i<pattern.length();i++) {
            product *= primes[pattern.charAt(i)-97];
        }
        return product;
    }

    public static boolean check_subs(String pattern, String text) {
        int len = pattern.length();
        int i;
        int product, product2;
        String sub = "";
        product = find_product(pattern);
        for(i=0;i<text.length()-len+1;i++) {
            sub = text.substring(i,i+len);
            product2 = find_product(sub);
            if(product==product2)
                return true;
        }
        return false;
    }
}
