/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excelparser;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/**
 *
 * @author Rumali
 * Please import jxl.jar to run this script.
 */
public class ExcelParser {
    private String inputFile;
//    String type array for storing all the values having tag <code>
    String[] data = null;
//    String type array for storing corresponding questions of the codes
    
    String[] data2 = null;
    String[] data3=null;
    int[] counts = null;
  
    public void setInputFile(String inputFile) {
        
        this.inputFile = inputFile;
    }
    
    public String[] read() throws IOException, BiffException {
        
        File inputWorkbook = new File(inputFile);
        Workbook w;
        
        try {
            
            w = Workbook.getWorkbook(inputWorkbook);
//            Getting the first sheet
            Sheet sheet = w.getSheet(0);
//            Gettting the number of rows in data and creating array of same size
            data = new String[sheet.getRows()];
            data2 = new String[sheet.getRows()];
            data3= new String[sheet.getRows()];
            counts = new int[sheet.getRows()];

            for(int j=0; j<10; j++) {
                
               
//                Getting values of first column. 
//                *Takes value as column, row*
                Cell cell = sheet.getCell(0, j);
                String s = cell.getContents();
//                Getting values of fourth column
                Cell cell1 = sheet.getCell(3, j);
//                Checking whether the strings from first cell have name code
                Cell cell2= sheet.getCell(1, j);
                
                int indexChecker = s.indexOf("code");
//                ======================================
//                Below portion used for several testing
//                ======================================
//                System.out.println(s);
//                System.out.println("code index: "+s.indexOf("code"));
                
//                System.out.println("new string");
//                boolean result = s.matches(".*?\\bcode\\b.*?");
//                System.out.println(result);
//                if (cell.getContents().matches(pattern)) {

//                =========================================================
                
//                If 'code' tag exists, taking the string into data array
                if (indexChecker>=0) {
                    data[j] = cell.getContents();
                    
                    int indexOfFirstOccurance = data[j].indexOf("code");
                    int indexOfSecondOccurance = data[j].indexOf("code", data[j].indexOf("code") + 1);

                    data[j] = data[j].substring(indexOfFirstOccurance+5, indexOfSecondOccurance-1);
                    
  
                    
                }
                
//                Taking the corresponding question of the code
                if (data[j]!=null) {
                    data2[j] = cell1.getContents();
                    data3[j] = cell2.getContents();
                    counts[j] = Integer.parseInt(data3[j]);
                }
                
                //else  data3[j]=cell2.getContents();
                else {
                    data2[j] = null;
                    data3[j] = null;
                }  
                        
             
                    /*if (data[j]!=null)
                    data3[j] = cell2.getContents();
                //else  data3[j]=cell2.getContents();
                else data3[j] = null; */
            
            }
            
//            Showing the result
            for (int j=0; j<data.length; j++){
                System.out.println(j+1+". Answer: "+data[j]);
                System.out.println("Question: "+data2[j]);
                System.out.println("AnswerScore: "+counts[j]);
                System.out.println("");
                
            }
        }
        catch (BiffException e) {
            e.printStackTrace();
        }
    return data;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, BiffException {
        // TODO code application logic here
        ExcelParser test = new ExcelParser();
        test.setInputFile("SampleData.xls");
        test.read();
        //test
        //git

    }


    
}
