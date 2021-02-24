package br.com.publico.gastos;

import br.com.publico.gastos.domain.model.AvaliacaoTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Nome", "Tipo Avaliação", "Data", "Status", "Nota", "Resultado"};
    static String SHEET = "AvaliacaoTemplate";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<AvaliacaoTemplate> excelToAvaliacao(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<AvaliacaoTemplate> avaliacaoTemplates = new ArrayList<AvaliacaoTemplate>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                //pular cabeçalho
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                AvaliacaoTemplate avaliacaoTemplate = new AvaliacaoTemplate();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            avaliacaoTemplate.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            avaliacaoTemplate.setNome(currentCell.getStringCellValue());
                            break;

                        case 2:
                            avaliacaoTemplate.setTipoAvaliacao(currentCell.getStringCellValue());
                            break;

                        case 3:
                            avaliacaoTemplate.setData(currentCell.getDateCellValue());
                            break;

                        case 4:
                            avaliacaoTemplate.setStatus(currentCell.getStringCellValue());
                            break;

                        case 5:
                            avaliacaoTemplate.setNota(currentCell.getNumericCellValue());
                            break;

                        case 6:
                            avaliacaoTemplate.setResultado(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                avaliacaoTemplates.add(avaliacaoTemplate);
            }

            workbook.close();

            return avaliacaoTemplates;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao verificar o arquivo Excel: " + e.getMessage());
        }
    }

}
