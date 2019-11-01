package yb.ecp.fast.infra.util;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil
{
  private static final Log logger = LogFactory.getLog(ExcelUtil.class);
  
  /* Error */
  public static HSSFWorkbook readXls(String filePath)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: new 2	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 3	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   12: astore_1
    //   13: aconst_null
    //   14: aload_1
    //   15: if_acmpeq +12 -> 27
    //   18: new 4	org/apache/poi/hssf/usermodel/HSSFWorkbook
    //   21: dup
    //   22: aload_1
    //   23: invokespecial 5	org/apache/poi/hssf/usermodel/HSSFWorkbook:<init>	(Ljava/io/InputStream;)V
    //   26: astore_2
    //   27: iconst_1
    //   28: anewarray 6	java/lang/AutoCloseable
    //   31: dup
    //   32: iconst_0
    //   33: aload_1
    //   34: aastore
    //   35: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   38: goto +71 -> 109
    //   41: astore_3
    //   42: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   45: ldc 10
    //   47: aload_3
    //   48: invokeinterface 11 3 0
    //   53: iconst_1
    //   54: anewarray 6	java/lang/AutoCloseable
    //   57: dup
    //   58: iconst_0
    //   59: aload_1
    //   60: aastore
    //   61: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   64: goto +45 -> 109
    //   67: astore_3
    //   68: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   71: ldc 10
    //   73: aload_3
    //   74: invokeinterface 11 3 0
    //   79: iconst_1
    //   80: anewarray 6	java/lang/AutoCloseable
    //   83: dup
    //   84: iconst_0
    //   85: aload_1
    //   86: aastore
    //   87: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   90: goto +19 -> 109
    //   93: astore 4
    //   95: iconst_1
    //   96: anewarray 6	java/lang/AutoCloseable
    //   99: dup
    //   100: iconst_0
    //   101: aload_1
    //   102: aastore
    //   103: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   106: aload 4
    //   108: athrow
    //   109: aload_2
    //   110: areturn
    // Line number table:
    //   Java source line #51	-> byte code offset #0
    //   Java source line #52	-> byte code offset #2
    //   Java source line #55	-> byte code offset #4
    //   Java source line #57	-> byte code offset #13
    //   Java source line #58	-> byte code offset #18
    //   Java source line #66	-> byte code offset #27
    //   Java source line #67	-> byte code offset #38
    //   Java source line #60	-> byte code offset #41
    //   Java source line #61	-> byte code offset #42
    //   Java source line #66	-> byte code offset #53
    //   Java source line #67	-> byte code offset #64
    //   Java source line #62	-> byte code offset #67
    //   Java source line #63	-> byte code offset #68
    //   Java source line #66	-> byte code offset #79
    //   Java source line #67	-> byte code offset #90
    //   Java source line #66	-> byte code offset #93
    //   Java source line #67	-> byte code offset #106
    //   Java source line #69	-> byte code offset #109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	filePath	String
    //   1	101	1	inputStream	java.io.InputStream
    //   3	107	2	workBook	HSSFWorkbook
    //   41	7	3	e	java.io.FileNotFoundException
    //   67	7	3	e	IOException
    //   93	14	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	27	41	java/io/FileNotFoundException
    //   4	27	67	java/io/IOException
    //   4	27	93	finally
    //   41	53	93	finally
    //   67	79	93	finally
    //   93	95	93	finally
  }
  
  /* Error */
  public static void writeXls(HSSFWorkbook workBook, String path)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 13	java/io/FileOutputStream
    //   5: dup
    //   6: aload_1
    //   7: invokespecial 14	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 15	org/apache/poi/hssf/usermodel/HSSFWorkbook:write	(Ljava/io/OutputStream;)V
    //   16: iconst_1
    //   17: anewarray 6	java/lang/AutoCloseable
    //   20: dup
    //   21: iconst_0
    //   22: aload_2
    //   23: aastore
    //   24: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   27: goto +71 -> 98
    //   30: astore_3
    //   31: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   34: ldc 16
    //   36: aload_3
    //   37: invokeinterface 11 3 0
    //   42: iconst_1
    //   43: anewarray 6	java/lang/AutoCloseable
    //   46: dup
    //   47: iconst_0
    //   48: aload_2
    //   49: aastore
    //   50: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   53: goto +45 -> 98
    //   56: astore_3
    //   57: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   60: ldc 16
    //   62: aload_3
    //   63: invokeinterface 11 3 0
    //   68: iconst_1
    //   69: anewarray 6	java/lang/AutoCloseable
    //   72: dup
    //   73: iconst_0
    //   74: aload_2
    //   75: aastore
    //   76: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   79: goto +19 -> 98
    //   82: astore 4
    //   84: iconst_1
    //   85: anewarray 6	java/lang/AutoCloseable
    //   88: dup
    //   89: iconst_0
    //   90: aload_2
    //   91: aastore
    //   92: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   95: aload 4
    //   97: athrow
    //   98: return
    // Line number table:
    //   Java source line #80	-> byte code offset #0
    //   Java source line #84	-> byte code offset #2
    //   Java source line #85	-> byte code offset #11
    //   Java source line #91	-> byte code offset #16
    //   Java source line #92	-> byte code offset #27
    //   Java source line #86	-> byte code offset #30
    //   Java source line #87	-> byte code offset #31
    //   Java source line #91	-> byte code offset #42
    //   Java source line #92	-> byte code offset #53
    //   Java source line #88	-> byte code offset #56
    //   Java source line #89	-> byte code offset #57
    //   Java source line #91	-> byte code offset #68
    //   Java source line #92	-> byte code offset #79
    //   Java source line #91	-> byte code offset #82
    //   Java source line #92	-> byte code offset #95
    //   Java source line #93	-> byte code offset #98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	workBook	HSSFWorkbook
    //   0	99	1	path	String
    //   1	90	2	outputStream	java.io.FileOutputStream
    //   30	7	3	e	java.io.FileNotFoundException
    //   56	7	3	e	IOException
    //   82	14	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	30	java/io/FileNotFoundException
    //   2	16	56	java/io/IOException
    //   2	16	82	finally
    //   30	42	82	finally
    //   56	68	82	finally
    //   82	84	82	finally
  }
  
  /* Error */
  public static java.io.InputStream getInputStream(HSSFWorkbook workBook)
  {
    // Byte code:
    //   0: new 17	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 18	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 15	org/apache/poi/hssf/usermodel/HSSFWorkbook:write	(Ljava/io/OutputStream;)V
    //   15: aload_1
    //   16: invokevirtual 19	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   19: astore_3
    //   20: new 20	java/io/ByteArrayInputStream
    //   23: dup
    //   24: aload_3
    //   25: invokespecial 21	java/io/ByteArrayInputStream:<init>	([B)V
    //   28: astore_2
    //   29: iconst_1
    //   30: anewarray 6	java/lang/AutoCloseable
    //   33: dup
    //   34: iconst_0
    //   35: aload_2
    //   36: aastore
    //   37: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   40: iconst_1
    //   41: anewarray 6	java/lang/AutoCloseable
    //   44: dup
    //   45: iconst_0
    //   46: aload_1
    //   47: aastore
    //   48: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   51: goto +86 -> 137
    //   54: astore_3
    //   55: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   58: new 22	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   65: ldc 24
    //   67: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_3
    //   71: invokestatic 26	org/apache/commons/lang3/exception/ExceptionUtils:getStackTrace	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   74: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: invokevirtual 27	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokeinterface 28 2 0
    //   85: iconst_1
    //   86: anewarray 6	java/lang/AutoCloseable
    //   89: dup
    //   90: iconst_0
    //   91: aload_2
    //   92: aastore
    //   93: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   96: iconst_1
    //   97: anewarray 6	java/lang/AutoCloseable
    //   100: dup
    //   101: iconst_0
    //   102: aload_1
    //   103: aastore
    //   104: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   107: goto +30 -> 137
    //   110: astore 4
    //   112: iconst_1
    //   113: anewarray 6	java/lang/AutoCloseable
    //   116: dup
    //   117: iconst_0
    //   118: aload_2
    //   119: aastore
    //   120: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   123: iconst_1
    //   124: anewarray 6	java/lang/AutoCloseable
    //   127: dup
    //   128: iconst_0
    //   129: aload_1
    //   130: aastore
    //   131: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   134: aload 4
    //   136: athrow
    //   137: aload_2
    //   138: areturn
    // Line number table:
    //   Java source line #104	-> byte code offset #0
    //   Java source line #105	-> byte code offset #8
    //   Java source line #108	-> byte code offset #10
    //   Java source line #109	-> byte code offset #15
    //   Java source line #110	-> byte code offset #20
    //   Java source line #114	-> byte code offset #29
    //   Java source line #115	-> byte code offset #40
    //   Java source line #116	-> byte code offset #51
    //   Java source line #111	-> byte code offset #54
    //   Java source line #112	-> byte code offset #55
    //   Java source line #114	-> byte code offset #85
    //   Java source line #115	-> byte code offset #96
    //   Java source line #116	-> byte code offset #107
    //   Java source line #114	-> byte code offset #110
    //   Java source line #115	-> byte code offset #123
    //   Java source line #116	-> byte code offset #134
    //   Java source line #118	-> byte code offset #137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	workBook	HSSFWorkbook
    //   7	123	1	byteArrayOutputStream	java.io.ByteArrayOutputStream
    //   9	129	2	inputStream	java.io.InputStream
    //   19	6	3	content	byte[]
    //   54	17	3	e	IOException
    //   110	25	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	29	54	java/io/IOException
    //   10	29	110	finally
    //   54	85	110	finally
    //   110	112	110	finally
  }
  
  /* Error */
  public static java.io.ByteArrayOutputStream getByteArrayOutputStream(HSSFWorkbook workBook)
  {
    // Byte code:
    //   0: new 17	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 18	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual 15	org/apache/poi/hssf/usermodel/HSSFWorkbook:write	(Ljava/io/OutputStream;)V
    //   13: iconst_1
    //   14: anewarray 6	java/lang/AutoCloseable
    //   17: dup
    //   18: iconst_0
    //   19: aload_1
    //   20: aastore
    //   21: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   24: goto +62 -> 86
    //   27: astore_2
    //   28: getstatic 9	yb/ecp/fast/infra/util/ExcelUtil:logger	Lorg/apache/commons/logging/Log;
    //   31: new 22	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   38: ldc 24
    //   40: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_2
    //   44: invokestatic 26	org/apache/commons/lang3/exception/ExceptionUtils:getStackTrace	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   47: invokevirtual 25	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 27	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokeinterface 28 2 0
    //   58: iconst_1
    //   59: anewarray 6	java/lang/AutoCloseable
    //   62: dup
    //   63: iconst_0
    //   64: aload_1
    //   65: aastore
    //   66: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   69: goto +17 -> 86
    //   72: astore_3
    //   73: iconst_1
    //   74: anewarray 6	java/lang/AutoCloseable
    //   77: dup
    //   78: iconst_0
    //   79: aload_1
    //   80: aastore
    //   81: invokestatic 7	yb/ecp/fast/infra/util/CloseUtils:close	([Ljava/lang/AutoCloseable;)V
    //   84: aload_3
    //   85: athrow
    //   86: aload_1
    //   87: areturn
    // Line number table:
    //   Java source line #130	-> byte code offset #0
    //   Java source line #133	-> byte code offset #8
    //   Java source line #137	-> byte code offset #13
    //   Java source line #138	-> byte code offset #24
    //   Java source line #134	-> byte code offset #27
    //   Java source line #135	-> byte code offset #28
    //   Java source line #137	-> byte code offset #58
    //   Java source line #138	-> byte code offset #69
    //   Java source line #137	-> byte code offset #72
    //   Java source line #138	-> byte code offset #84
    //   Java source line #140	-> byte code offset #86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	workBook	HSSFWorkbook
    //   7	80	1	byteArrayOutputStream	java.io.ByteArrayOutputStream
    //   27	17	2	e	IOException
    //   72	13	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   8	13	27	java/io/IOException
    //   8	13	72	finally
    //   27	58	72	finally
  }
  
  public static HSSFSheet getSheet(HSSFWorkbook workBook, String sheetName)
  {
    HSSFSheet sheet = workBook.getSheet(sheetName);
    if (null == sheet) {
      sheet = workBook.createSheet(sheetName);
    }
    return sheet;
  }
  
  public static HSSFRow getRow(HSSFSheet sheet, int rowIndex)
  {
    HSSFRow row = sheet.getRow(rowIndex);
    if (row == null) {
      row = sheet.createRow(rowIndex);
    }
    return row;
  }
  
  public static HSSFCell getCell(HSSFRow row, int colIndex)
  {
    HSSFCell cell = row.getCell(colIndex);
    if (cell == null) {
      cell = row.createCell(colIndex);
    }
    return cell;
  }
  
  public static HSSFCell getCell(HSSFSheet sheet, int rowIndex, int colIndex)
  {
    HSSFRow row = getRow(sheet, rowIndex);
    return getCell(row, colIndex);
  }
  
  public static void setSheetCellValue(HSSFSheet sheet, int rowIndex, int colIndex, String value, int cellType)
  {
    HSSFCell cell = getCell(sheet, rowIndex, colIndex);
    setCellValue(cell, value, cellType);
  }
  
  public static void setSheetCellValue(HSSFSheet sheet, int rowIndex, int colIndex, int value)
  {
    HSSFCell cell = getCell(sheet, rowIndex, colIndex);
    setCellValue(cell, value);
  }
  
  public static void setSheetCellValue(HSSFSheet sheet, int rowIndex, int colIndex, long value)
  {
    HSSFCell cell = getCell(sheet, rowIndex, colIndex);
    setCellValue(cell, value);
  }
  
  public static void setSheetCellValue(HSSFSheet sheet, int rowIndex, int colIndex, String value)
  {
    HSSFCell cell = getCell(sheet, rowIndex, colIndex);
    setCellValue(cell, value);
  }
  
  public static void setCellValue(HSSFCell cell, String value, int cellType)
  {
    cell.setCellType(cellType);
    switch (cell.getCellType())
    {
    case 1: 
      cell.setCellValue(value);
      break;
    case 0: 
      cell.setCellValue(Integer.parseInt(value));
      break;
    case 2: 
      cell.setCellFormula(value);
      break;
    default: 
      cell.setCellValue(value);
    }
  }
  
  public static void setCellValue(HSSFCell cell, int value)
  {
    cell.setCellType(0);
    cell.setCellValue(value);
  }
  
  public static void setCellValue(HSSFCell cell, long value)
  {
    cell.setCellType(0);
    cell.setCellValue(value);
  }
  
  public static void setCellValue(HSSFCell cell, String value)
  {
    cell.setCellType(1);
    cell.setCellValue(value);
  }
  
  public static String getSheetCellValue(HSSFSheet sheet, int rowIndex, int colIndex)
  {
    HSSFCell cell = getCell(sheet, rowIndex, colIndex);
    return getCellStringValue(cell);
  }
  
  public static String getCellStringValue(HSSFCell cell)
  {
    String cellValue = "";
    switch (cell.getCellType())
    {
    case 1: 
      cellValue = cell.getStringCellValue();
      break;
    case 0: 
      cellValue = String.valueOf(cell.getNumericCellValue());
      break;
    case 2: 
      cell.setCellType(0);
      cellValue = String.valueOf(cell.getNumericCellValue());
      break;
    case 3: 
      break;
    case 4: 
      cellValue = String.valueOf(cell.getBooleanCellValue());
      break;
    case 5: 
      break;
    }
    return cellValue;
  }
  
  public static void mergeCell(HSSFSheet sheet, int rowFrom, int rowTo, int columnFrom, int columnTo)
  {
    CellRangeAddress crAddress = new CellRangeAddress(rowFrom, rowTo, columnFrom, columnTo);
    sheet.addMergedRegion(crAddress);
  }
  
  public static void setCellStyle(HSSFWorkbook workBook, HSSFCell cell, short styleType)
  {
    HSSFCellStyle cellStyle = workBook.createCellStyle();
    cellStyle.setAlignment(styleType);
    cell.setCellStyle(cellStyle);
  }
  
  public static double calculateFormulaValue(HSSFWorkbook workBook, HSSFCell cell)
  {
    double retValue = 0.0D;
    if (cell.getCellType() == 2)
    {
      HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(workBook);
      CellValue cellValue = evaluator.evaluate(cell);
      retValue = cellValue.getNumberValue();
    }
    return retValue;
  }
  
  public static void resetFormulaCell(HSSFSheet sheet, int rowIndex, int colIndex)
  {
    HSSFCell hssfCell = getCell(sheet, rowIndex, colIndex);
    if (2 == hssfCell.getCellType()) {
      hssfCell.setCellFormula(hssfCell.getCellFormula());
    }
  }
  
  public static void resetFormulaCell(HSSFCell hssfCell)
  {
    if (2 == hssfCell.getCellType()) {
      hssfCell.setCellFormula(hssfCell.getCellFormula());
    }
  }
  
  public static int convertToColumnIndex(String letterName)
  {
    int column = -1;
    letterName = StringUtils.upperCase(letterName);
    for (int i = 0; i < letterName.length(); i++)
    {
      int c = letterName.charAt(i);
      column = (column + 1) * 26 + c - 65;
    }
    return column;
  }
  
  public static String convertToLetterName(int columnIndex)
  {
    columnIndex++;
    String strResult = "";
    int intRound = columnIndex / 26;
    int intMod = columnIndex % 26;
    if (intRound != 0) {
      strResult = String.valueOf((char)(intRound + 64));
    }
    strResult = strResult + String.valueOf((char)(intMod + 64));
    
    return strResult;
  }
  
  public static String convertToCellId(int columnIndex, int rowIndex)
  {
    return convertToLetterName(columnIndex) + (rowIndex + 1);
  }
  
  public static void delSheet(HSSFWorkbook workBook, String sheetName)
  {
    int sheetIndex = workBook.getSheetIndex(sheetName);
    workBook.removeSheetAt(sheetIndex);
  }
  
  public static void writeExcel(String fileName, byte[] bytes, HttpServletResponse response)
    throws Exception
  {
    try
    {
      response.setContentType("application/x-msdownload");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
      response.setContentLength(bytes.length);
      response.getOutputStream().write(bytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
    }
    catch (IOException localIOException) {}
  }
}
