package com.myproject.automocao.service;

import com.myproject.automocao.domain.Data;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordService {


    public ResponseEntity<?> getWord(Data data) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = paragraph.createRun();
        run.setText("AVALIAÇÃO DE EXPERIÊNCIA DO CLIENTE");
        run.setFontSize(14);
        run.setBold(true);

        XWPFTable table1 = document.createTable(2,1);

        table1.getCTTbl().getTblPr().unsetTblBorders();

        table1.getRow(0).getCell(0).setText("IMÓVEL");
        table1.getRow(1).getCell(0).setText(data.addrress());
        table1.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table1.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table1.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table1.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        table1.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table1.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        CTAbstractNum abstractNum = CTAbstractNum.Factory.newInstance();
        abstractNum.setAbstractNumId(BigInteger.valueOf(0));

        CTLvl lvl = abstractNum.addNewLvl();
        lvl.setIlvl(BigInteger.ZERO);
        lvl.addNewNumFmt().setVal(STNumberFormat.UPPER_ROMAN);
        lvl.addNewLvlText().setVal("%1 -");
        lvl.addNewStart().setVal(BigInteger.ONE);

        XWPFNumbering numbering = document.createNumbering();
        XWPFAbstractNum abs = new XWPFAbstractNum(abstractNum);
        BigInteger abstractNumID = numbering.addAbstractNum(abs);
        BigInteger numID = numbering.addNum(abstractNumID);

        XWPFParagraph p1 = document.createParagraph();
        p1.setNumID(numID);
        p1.createRun().setText("VISITANTE:");

        XWPFTable table2 = document.createTable(2, 2);
        table2.getRow(0).getCell(0).setText("NOME: " + data.visitor().getName());
        table2.getRow(1).getCell(0).setText("EMAIL: " + data.visitor().getEmail());
        table2.getRow(1).getCell(1).setText("TELEFONE: " + data.visitor().getTell());
        table2.getRow(0).getCell(1).setText("CPF: " + data.visitor().getCPF());

        table2.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table2.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table2.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table2.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        table2.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table2.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        XWPFParagraph p2 = document.createParagraph();
        p2.setNumID(numID);
        p2.createRun().setText("- AVALIAÇÃO:");
        List<String> avaliacoes = List.of(
                "Avaliação do imóvel",
                "Localização",
                "Tamanho",
                "Planta (disposição dos cômodos)",
                "Qualidade / Acabamentos",
                "Estado de Conservação",
                "Áreas comuns",
                "Preço"
        );

        XWPFTable table3 = document.createTable(8, 6);


        for (int i = 0; i < 8; i++) {
            XWPFTableRow row = table3.getRow(i);
            for (int j = 0; j < 6; j++) {
                XWPFTableCell cell = row.getCell(j);

                CTTcPr tcPr = cell.getCTTc().isSetTcPr() ? cell.getCTTc().getTcPr() : cell.getCTTc().addNewTcPr();
                CTTblWidth tcW = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();

                if (j == 0) {
                    tcW.setW(BigInteger.valueOf(2865));
                    if (i == 0) {
                        cell.setText("");
                    } else {
                        cell.setText(avaliacoes.get(i));
                    }
                } else {
                    tcW.setW(BigInteger.valueOf(136));
                    if (i == 0) {
                        cell.setText(String.valueOf(j));
                    } else {
                        int nota = switch (i) {
                            case 1 -> data.assessment().getLocation();
                            case 2 -> data.assessment().getSize();
                            case 3 -> data.assessment().getPropertyPlan();
                            case 4 -> data.assessment().getQuality();
                            case 5 -> data.assessment().getConservationState();
                            case 6 -> data.assessment().getCommonAreas();
                            case 7 -> data.assessment().getPrice();
                            default -> -1;
                        };
                        if (toverify(j, nota)) {
                            cell.setText("X");
                        }
                    }
                }

                tcW.setType(STTblWidth.DXA); // largura fixa
            }
        }
        table3.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table3.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table3.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table3.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table3.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table3.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");


        // Adiciona um parágrafo vazio entre as tabelas
        XWPFParagraph spacer = document.createParagraph();
        spacer.setSpacingAfter(200);

        XWPFTable table4 = document.createTable(3, 2);

        table4.getRow(0).getCell(0).setText("O que mais gostou?");
        table4.getRow(0).getCell(1).setText(data.assessment().getLikeMost());
        table4.getRow(1).getCell(0).setText("O que menos gostou? ");
        table4.getRow(1).getCell(1).setText(data.assessment().getLikeLeast());
        table4.getRow(2).getCell(0).setText("Compraria este imóvel?");
        table4.getRow(2).getCell(1).setText(data.assessment().getBuyAgain() == true ? "sim" : "não");

        table4.setTopBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table4.setBottomBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table4.setLeftBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table4.setRightBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        table4.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");
        table4.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 4, 0, "000000");

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.write(out);
            document.close();

            byte[] bytes = out.toByteArray();
            out.close();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=avaliacao.docx")
                    .header("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                    .body(bytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao gerar o documento.");
        }



    }

    private boolean toverify(int j, int price) {
        if (j == price){
            return true;
        }
        return false;
    }

}