/**
*
* @author Eren
* @since 28.02.2019 23:22
* <p>
* 
* </p>
*/

package deneme;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class Deneme {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("Program.c"));
            String line;
            while((line = br.readLine()) != null){
                if(sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contents = sb.toString();
        //System.out.println(contents); denemek için yazdırdım.
        
        //Operatör saymak için gerekli kod bloğu.
        int operatorSayac = 0;
        String opRegex = "(\\+= | \\== | \\*= | \\-= |\\+| \\- | \\/= |=| \\*|!|&|\\/|<|>)";
        Pattern pattern = Pattern.compile(opRegex);
        Matcher matcher = pattern.matcher(contents);
        
        while(matcher.find()) {
            operatorSayac++;
        }
        
        //Metotları saymak için gerekli kod bloğu.
        int metotSayac = 0;
        String metRegex = "(void) \\w+[a-zA-Z]\\b\\(|(int) \\w+[a-zA-Z]\\b\\(|(String) \\w+[a-zA-Z]\\b\\(";
        Pattern pattern2 = Pattern.compile(metRegex);
        Matcher matcher2 = pattern2.matcher(contents);

        while(matcher2.find()) {
            metotSayac++;
        }
        
        
        //Parametreleri saymak ve isimlerini almak için gerekli kod bloğu.   kaybetmemek için şurada dursun** "(\\(\\b.*[\\w\\s,$]\\b\\,))(\\b.*[\\w\\s,$]\\b\\)"
        int parametreSayac = 0;
        String parametreRegex = "(\\(int\\b.*[\\w\\s,$]\\b\\,)(int\\b.*[\\w\\s,$]\\b\\))"; //parantez içini alıyor parametre kısmında  ** şurada dursun "\\b\\((int) .+\\b\\)"
        Pattern pattern3 = Pattern.compile(parametreRegex);
        Matcher matcher3 = pattern3.matcher(contents);
        
        String[] a = new String[metotSayac];
        String[] b = new String[metotSayac];
        
        //parametreleri regex ile 2 gruba ayırdım ve diziye attım.
        int sayacP = 0;
        while(matcher3.find()) {
            a[sayacP] = matcher3.group(1);
            parametreSayac++;
            b[sayacP] = matcher3.group(2);
            parametreSayac++;
            sayacP++;
        }
        
        //(int)\h[a-zA-Z]\w+\b\(|(void)\h[a-zA-Z]\w+
        //[^\s\(] bu ikisini birleştirebilirsem metot isimlerini çok güzel alır.
        
        //Metot isimlerini almak için gerekli kod bloğu. 
        String metotIsimRegex = "(void)\\h[a-zA-Z]\\w+|(int)\\h[a-zA-Z]\\w+\\b\\(|(String) \\w+[a-zA-Z]\\b\\("; //bu regex daha güzel çalıştı.
        //(void) \\w+|(int) \\w+\\b\\( şimdilik kalsın burası
        Pattern pattern4 = Pattern.compile(metotIsimRegex);
        Matcher matcher4 = pattern4.matcher(contents);
        
        String[] s = new String[metotSayac];
        
        int sayacM = 0;
        while(matcher4.find()){
                s[sayacM] = matcher4.group();
                sayacM++;
        }
        
        System.out.println("Operatör Sayısı : "+operatorSayac);
        System.out.println("Toplam Metot Sayısı : "+metotSayac);
        System.out.println("Toplam Parametre Sayısı : "+parametreSayac);
        System.out.println("Fonksiyon İsimleri : ");
        int sayacParam = 0;
        for (String item : s) {
            System.out.println(item+" - Parametreler: "+a[sayacParam]+" "+b[sayacParam]);
            sayacParam++;
        }
    }
}
