package br.com.publico.gastos;


import br.com.publico.gastos.domain.model.*;
import br.com.publico.gastos.services.ValidacoesService;
import br.com.publico.gastos.services.exception.ColaboradorPossuiAvaliacaoException;
import br.com.publico.gastos.services.exception.DomainException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.index.qual.SearchIndexBottom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelHelper {


    @Autowired
    private ValidacoesService validacoesService;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Nome", "Sigla", "Tipo Avaliação", "Data", "Status", "Nota", "Resultado"};
    static String SHEET = "Carreiras";


    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public List<Avaliacao> excelToAvaliacao(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Avaliacao> avaliacaoList = new ArrayList<>();

            String nome = "Nome";
            int nomeIndx = 0;
            String sigla = "Sigla";
            int siglaIndx = 0;
            String tipoAvaliacao = "Tipo Avaliação";
            int tipoAvaliacaoIndx = 0;
            String data = "Data";
            int dataIndx = 0;
            String status = "Status";
            int statusIndx = 0;
            String nota = "Nota";
            int notaIndx = 0;
            String resultado = "Resultado";
            int resultadoIndx = 0;

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();



                //pular cabeçalho e validar indices
                if (rowNumber == 0) {

                    Iterator<Cell> cellsInRow = currentRow.iterator();

                    int cellIdx = 0;
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();

                        if(currentCell.getStringCellValue().equalsIgnoreCase(nome)) {
                            nomeIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(sigla)) {
                            siglaIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(tipoAvaliacao)) {
                            tipoAvaliacaoIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(data)) {
                            dataIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(status)) {
                            statusIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(nota)) {
                            notaIndx = currentCell.getColumnIndex();
                        }

                        if(currentCell.getStringCellValue().equalsIgnoreCase(resultado)) {
                            resultadoIndx = currentCell.getColumnIndex();
                        }

                        cellIdx++;
                    }
                    rowNumber++;
                    continue;
                }
                rowNumber++;

                Avaliacao avaliacao = new Avaliacao();
                Colaborador colaborador = new Colaborador();

                avaliacao.setColaborador(colaborador);
                avaliacao.getColaborador().setNome(currentRow.getCell(nomeIndx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                avaliacao.getColaborador().setSigla(currentRow.getCell(siglaIndx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());

                switch (currentRow.getCell(tipoAvaliacaoIndx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()) {
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

                    default:
                        throw new DomainException("Linha " +  rowNumber + ": Tipo de Avaliação difere do esperado: 1:1/AD/1-on-1, Experiência 45d," +
                                " Experiência 90d, Reconhecimento ou Formação");
                }

                avaliacao.setData(currentRow.getCell(dataIndx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getLocalDateTimeCellValue().toLocalDate());

                switch (currentRow.getCell(statusIndx,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()) {
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

                    default:
                        throw new DomainException("Linha " +  rowNumber + ": Status difere do esperado: Novo, Agendado, Formalizar, Em aprovação, Finalizado ou Desligado");
                }

                CellType tipoCelula = currentRow.getCell(notaIndx).getCellType();
                if (tipoCelula == CellType.STRING) {

                avaliacao.setNota(new BigDecimal(currentRow.getCell(notaIndx,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()));

                }

                if (tipoCelula == CellType.NUMERIC) {

                    avaliacao.setNota(BigDecimal.valueOf(currentRow.getCell(notaIndx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue()));

                }

                switch (currentRow.getCell(resultadoIndx,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()) {
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

                    default:
                        throw new DomainException("Linha " +  rowNumber + ": Resultado difere do esperado: Mérito, Promoção, Adequação ou N/A");
                }



                if (!validacoesService.verificaAvaliacaoExistente(avaliacao.getColaborador().getNome(), avaliacao.getColaborador().getSigla(), avaliacao.getData())) {
                    avaliacaoList.add(avaliacao);
                } else {
                    throw new ColaboradorPossuiAvaliacaoException(rowNumber, avaliacao.getColaborador().getNome(), avaliacao.getData().getMonthValue());
                }
            }

            workbook.close();

            return avaliacaoList;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao verificar o arquivo Excel: " + e.getMessage());
        }
    }

}
