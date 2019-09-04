package michalwds.extras;

//Type T (class) will give poles with all attributes. it has to be class

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


//creating files XLS to excel with all objects after iterations and same names as columns from DB. It helps later to load to file CVS for example as list of objects
public class CreatorXLS<T> {

    private Class clazz;


    //we give to the constructor a class and use it. (we can use all class you want)
    public CreatorXLS(Class clazz) {
        this.clazz = clazz;
    }


    //creating file xls using list of Objects (records)
    public void createFile(List<T> series, String path, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        // creating object which represent whole file excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        // creating sheet and overloading method, givin a name of file (fileName)

        HSSFSheet sheet = workbook.createSheet(fileName);

        //todo - dodać style arkusza

        List<String> columns = new ArrayList<>(); //same columns like in database

        //iteration after columns and declaring name of this field
        for (Field f : clazz.getDeclaredFields()) {
            columns.add(f.getName());
        }

        //reading(iterating) from left to right all fields and add to empty fields in table(rows)

        Row header = sheet.createRow(0);
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns.get(i));  //creating empty columns (tables)

            //todo - uzupełnić style cell
        }
        for (int i = 0; i < series.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1); //creating that much rows as many objects we have, always starts from 1 because 0 is header

            for (int j = 0; j < columns.size(); j++) {
                HSSFCell cell = row.createCell(j);

                Method method = series.get(i)
                        .getClass()
                        .getMethod("get" + columns.get(j)
                                .substring(0, 1)
                                .toUpperCase() + columns.get(j)
                                .substring(1));

                Object result = method.invoke(series.get(i));
                cell.setCellValue(String.valueOf(result));
            }
        }

        long mills = System.currentTimeMillis();
        String file = path = fileName + "_" + mills + ".xls";
        workbook.write(new File(file));
        workbook.close();
    }


}

