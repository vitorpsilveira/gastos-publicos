package br.com.publico.gastos;

import br.com.publico.gastos.domain.model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Nome", "Sigla", "Tipo Avaliação", "Data", "Status", "Nota", "Resultado"};
    static String SHEET = "AvaliacaoTemplate";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Avaliacao> excelToAvaliacao(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Avaliacao> avaliacaos = new ArrayList<Avaliacao>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                //pular cabeçalho
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Avaliacao avaliacao = new Avaliacao();
                Colaborador colaborador = new Colaborador();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();


                    switch (cellIdx) {
                        case 0:
                            avaliacao.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            avaliacao.setColaborador(colaborador);
                            avaliacao.getColaborador().setNome(currentCell.getStringCellValue());
                            break;

                        case 2:
                            avaliacao.setColaborador(colaborador);
                            avaliacao.getColaborador().setSigla(currentCell.getStringCellValue());
                            break;

                        case 3:
                            switch (currentCell.getStringCellValue()) {
                                case "1:1":
                                case "AD":
                                case "1-on-1":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.ONE_TO_ONE);
                                    break;

                                case "Informal":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.INFORMAL);
                                    break;

                                case "Experiência 45d":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.EXPERIENCIA45D);
                                    break;

                                case "Experiência 90d":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.EXPERIENCIA90D);
                                    break;

                                case "Reconhecimento":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.RECONHECIMENTO);
                                    break;

                                case "Formação":
                                    avaliacao.setTipoAvaliacao(TipoAvaliacao.FORMACAO);
                                    break;
                            }
                            break;

                        case 4:
                            avaliacao.setData(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 5:
                            switch (currentCell.getStringCellValue()) {
                                case "Novo":
                                    avaliacao.setStatus(Status.NOVO);
                                    break;

                                case "Agendado":
                                    avaliacao.setStatus(Status.AGENDADO);
                                    break;

                                case "Formalizar":
                                    avaliacao.setStatus(Status.FORMALIZAR);
                                    break;

                                case "Em aprovação":
                                    avaliacao.setStatus(Status.EM_APROVACAO);
                                    break;

                                case "Finalizado":
                                    avaliacao.setStatus(Status.FINALIZADO);
                                    break;

                                case "Desligado":
                                    avaliacao.setStatus(Status.DESLIGADO);
                                    break;
                            }
                            break;

                        case 6:
                            avaliacao.setNota(new BigDecimal(currentCell.getStringCellValue()));
                            break;

                        case 7:
                            switch (currentCell.getStringCellValue()) {
                                case "Mérito":
                                    avaliacao.setResultado(TipoResultado.MERITO);
                                    break;

                                case "Promoção":
                                    avaliacao.setResultado(TipoResultado.PROMOCAO);
                                    break;

                                case "Adequação":
                                    avaliacao.setResultado(TipoResultado.ADEQUACAO);
                                    break;

                                case "N/A":
                                    avaliacao.setResultado(TipoResultado.NA);
                                    break;
                            }
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                avaliacaos.add(avaliacao);
            }

            workbook.close();

            return avaliacaos;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao verificar o arquivo Excel: " + e.getMessage());
        }
    }

}
