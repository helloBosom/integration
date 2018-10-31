package fun.peri.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ImportExcelUtil {
    private static final int ROW_NUM_DEFAULT = 0;

    public static List<Map> readHeader(File newFile, int headerCount) {
        //new ArrayList<>();
        List<Map> headers = null;
        try {
            headers = createList(newFile, headerCount);
            ImportExcel importExcel = new ImportExcel(newFile, ROW_NUM_DEFAULT);
            for (int j = headerCount - 1; j >= 0; j--) {
                Row row = importExcel.getRow(j);
                if (row == null) {
                    continue;
                }
                Cell cell = null;
                String value;
                Map map = null;
                String tempName = null;
                for (int i = row.getFirstCellNum(); i <= row.getLastCellNum(); i++) {

//                    System.out.println("col is " +i);
                    cell = row.getCell(i);
                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                        System.out.println("CELL_TYPE_BLANK col is " +i);

                        if (headers.get(i) != null) {
                            Map m = headers.get(i);
                            String name = m.get("name").toString();
                            name = tempName + "-" + name;
                            m.put("name", name);
                            headers.set(i, m);
                        }
                        //当该单元格为空
                        if (i != row.getLastCellNum()) {//判断是否是该行中最后一个单元格
//                        headers.add(null);
                        }
                        continue;
                    }
                    tempName = cell.getStringCellValue();
                    map = makeColumns(cell);
                    if (headers.get(i) == null) {
                        headers.set(i, map);
                    } else {
                        String name = headers.get(i).get("name").toString();
//                        name = map.get("name").toString() + "-" + name;
                        name = tempName + "-" + name;
                        map.put("name", name);
                        headers.set(i, map);
                    }

                }
            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
            headers = null;
        } catch (IOException e) {
            e.printStackTrace();
            headers = null;
        }
        return headers;
    }


    private static List<Map> createList(File newFile, int headerCount) throws IOException, InvalidFormatException {
        List<Map> headers = new ArrayList<Map>();
        ImportExcel importExcel = new ImportExcel(newFile, ROW_NUM_DEFAULT);
        Row row = importExcel.getRow(headerCount - 1);
        int colNum = row.getLastCellNum();
        for (int i = 0; i <= colNum; i++) {
            headers.add(null);
        }
        return headers;
    }

    private static Map makeColumns(Cell cell) {
        Map map = new HashMap();

        String name;
        String type = "Unlimited";
        int maxLen;
        String maxSelected;
        String unit;
        boolean required;
        name = cell.getStringCellValue();
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                type = "Digit";
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                type = "Unlimited";
            } else {
                type = "Unlimited";
            }
        }
        maxLen = 200; // default
        maxSelected = "";
        unit = "";
        required = false;
        map.put("name", name);
        map.put("type", type);
        map.put("maxLen", maxLen);
        map.put("maxSelected", maxSelected);
        map.put("unit", unit);
        map.put("required", required);
        map.put("promptMessage", "");
        return map;
    }

    public static void main(String[] args) {
        String fileName = "d:\\template1.xlsx";
        File file = new File(fileName);
        List<Map> headers = ImportExcelUtil.readHeader(file, 4);
        int i = 0;
        for (Map m : headers) {
            if (null != m) {
                System.out.println("第" + i + "表头:");
                System.out.print("name:" + m.get("name"));
                System.out.print("   ");
                System.out.print("type:" + m.get("type"));
                System.out.print("   ");
                System.out.print("maxLen:" + m.get("maxLen"));
                System.out.print("   ");
                System.out.print("required:" + m.get("required"));
                System.out.print("   ");
                System.out.println();
            }

            i++;
        }
    }
}
