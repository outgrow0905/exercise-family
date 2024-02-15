package com.latte.cj.hwp.service;

import com.latte.cj.royalty.model.RoyaltyCode;
import com.latte.cj.royalty.model.registrationinfo.Response;
import java.io.File;
import java.io.FileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoyaltyExcelProducer {

    @Autowired
    private KiprisService kiprisService;

    public File produce() {
        // Workbook 객체 생성
        Workbook workbook = new HSSFWorkbook();

        // Excel Sheet1 생성
        Sheet sheet1 = workbook.createSheet("royalty");

        // Sheet1 Head write
        Row headRow = sheet1.createRow(0);
        sheet1.setColumnWidth(0, 5000);
        Cell headRowCell = headRow.createCell(0);

        headRowCell.setCellValue("fileName");
        headRowCell = headRow.createCell(1);
        headRowCell.setCellValue("royaltyCode");
        headRowCell = headRow.createCell(2);
        headRowCell.setCellValue("titleOfInvention");
        headRowCell = headRow.createCell(3);
        headRowCell.setCellValue("applicationNumber");
        headRowCell = headRow.createCell(4);
        headRowCell.setCellValue("finalDisposal");
        headRowCell = headRow.createCell(5);
        headRowCell.setCellValue("registerStatus");

        for (int i = 0 ; i < RoyaltyCode.getRoyaltyCodes().size() ; i++){
            Row bodyRow = sheet1.createRow(i+1);

            // file name
            Cell cell = bodyRow.createCell(0);
            cell.setCellValue(RoyaltyCode.getFileName());

            // royalty code
            cell = bodyRow.createCell(1);
            cell.setCellValue(RoyaltyCode.getRoyaltyCodes().get(i));

            // registration info
            Response registrationInfo = kiprisService.getRegistrationInfo(
                RoyaltyCode.getRoyaltyCodes().get(i));
            cell = bodyRow.createCell(2);
            cell.setCellValue(registrationInfo.getBody().getItems().getRegistrationInfo().getRegistrationRightInfo().getTitleOfInvention());

            // applicationNumber
            cell = bodyRow.createCell(3);
            String applicationNumber = registrationInfo.getBody().getItems().getRegistrationInfo()
                .getRegistrationRightInfo().getApplicationNumber();
            cell.setCellValue(applicationNumber);

            // finalDisposal
            com.latte.cj.royalty.model.royaltystatus.Response royaltyStatus = kiprisService.getRoyaltyStatus(
                applicationNumber);
            cell = bodyRow.createCell(4);
            cell.setCellValue(royaltyStatus.getBody().getItems().getItem().getFinalDisposal());

            // registerStatus
            cell = bodyRow.createCell(5);
            cell.setCellValue(royaltyStatus.getBody().getItems().getItem().getRegisterStatus());
        }

        // File 생성
        File file = new File("./" + RoyaltyCode.getFileName() + "_" + "royalty.xls");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ((HSSFWorkbook) workbook).write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.debug("finally end");
        }

        return file;
    }
}
