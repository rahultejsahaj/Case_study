// create an excel file by read and clean an Excel file in .xlsx format (by comparing it to a master list, also in Excel format) before writing it to a new Excel file
public class ReadAndCleanExcel {

    public void readExcel(String filePath) {

        Object[][] data = null;
        final DataFormatter df = new DataFormatter();

        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            int rownum = 0;
            int colnum = 0;
            Row r=rowIterator.next();

            int rowcount=sheet.getLastRowNum();
            int colcount=r.getPhysicalNumberOfCells();

            data = new Object[rowcount][colcount];

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                colnum = 0;

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    data[rownum][colnum] =  df.formatCellValue(cell);
                    System.out.print(df.formatCellValue(cell).toUpperCase() + " ");
                    colnum++;

                }

                rownum++;
                System.out.println();

        }     
            file.close();
            workbook.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}


// data object is created and iris data is used.

// testing default data set
Dataset data = new DefaultDataset();
for (int i = 0; i < 10; i++) {
    Instance tmpInstance = InstanceTools.randomInstance(25);
    data.add(tmpInstance);
}

//load iris data after pre-processing 
/* Load a data set */
Dataset data = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
 /* Contruct a KNN classifier that uses 5 neighbors to make a
  *decision. */
Classifier knn = new KNearestNeighbors(5);
knn.buildClassifier(data);

//classfication 
 Dataset dataForClassification = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
/* Counters for correct and wrong predictions. */
int correct = 0, wrong = 0;
/* Classify all instances and check with the correct class values */
for (Instance inst : dataForClassification) {
    Object predictedClassValue = knn.classify(inst);
    Object realClassValue = inst.classValue();
    if (predictedClassValue.equals(realClassValue))
        correct++;
    else
        wrong++;
}



































