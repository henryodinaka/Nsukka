package nsk.cath.com.utility;

/**
 *
 * @author LEOGOLD
 */
public class StringManipulation {
    
    public static String commaSeprated(Object[] items){
        StringBuilder sb = new StringBuilder();
        for(Object item: items){
            sb.append(item).append(",");
        }
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
