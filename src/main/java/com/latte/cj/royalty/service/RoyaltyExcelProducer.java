package com.latte.cj.royalty.service;

import com.latte.cj.hwp.service.KiprisService;
import com.latte.cj.royalty.model.RoyaltyCode;
import com.latte.cj.royalty.model.dto.RoyaltyCodeExcelDto;
import com.latte.cj.royalty.model.registrationinfo.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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

    public File produce(RoyaltyCodeExcelDto royaltyCodeExcelDto) {
        // Workbook 객체 생성
        Workbook workbook = new HSSFWorkbook();

        // Sheet1
        Sheet sheet1 = createSheet(workbook, "특허");

        // Sheet1 Header
        createSheet1Header(sheet1);

        // Sheet1 Body
        createSheet1Body(sheet1, royaltyCodeExcelDto, List.copyOf(royaltyCodeExcelDto.getRoyaltyCodes()));

        // Sheet2
        Sheet sheet2 = createSheet(workbook, "법");

        // Sheet2 Header
        createSheet2Header(sheet2);

        // Sheet2 Body
        createSheet2Body(sheet2, royaltyCodeExcelDto, List.copyOf(royaltyCodeExcelDto.getLaws()));

        // File 생성
        File file = new File("./" + royaltyCodeExcelDto.getTitle() + "_" + "royalty.xls");
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

    private void createSheet2Body(Sheet sheet1, RoyaltyCodeExcelDto royaltyCodeExcelDto, List<String> laws) {
        for (int i = 0; i < laws.size(); i++){
            Row bodyRow = sheet1.createRow(i + 1);

            // file name
            Cell cell = bodyRow.createCell(0);
            cell.setCellValue(royaltyCodeExcelDto.getTitle());

            // law
            cell = bodyRow.createCell(1);
            cell.setCellValue(laws.get(i));
        }
    }

    private void createSheet2Header(Sheet sheet2) {
        Row headRow = sheet2.createRow(0);
        sheet2.setColumnWidth(0, 5000);
        Cell headRowCell = headRow.createCell(0);
        headRowCell.setCellValue("fileName");
        headRowCell = headRow.createCell(1);
        headRowCell.setCellValue("law");
    }

    private void createSheet1Body(Sheet sheet1, RoyaltyCodeExcelDto royaltyCodeExcelDto,
        List<String> royaltyCodes) {
        for (int i = 0; i < royaltyCodes.size(); i++){
            Row bodyRow = sheet1.createRow(i + 1);

            // file name
            Cell cell = bodyRow.createCell(0);
            cell.setCellValue(royaltyCodeExcelDto.getTitle());

            // royalty code
            cell = bodyRow.createCell(1);
            cell.setCellValue(royaltyCodes.get(i));

            // registration info
            Response registrationInfo = kiprisService.getRegistrationInfo(royaltyCodes.get(i));
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

            // expirationDate
            cell = bodyRow.createCell(6);
            cell.setCellValue(registrationInfo.getBody().getItems().getRegistrationInfo()
                .getRegistrationRightInfo().getExpirationDate());

            // terminationCauseName
            cell = bodyRow.createCell(7);
            cell.setCellValue(registrationInfo.getBody().getItems().getRegistrationInfo()
                .getRegistrationRightInfo().getTerminationCauseName());

            // terminationDate
            cell = bodyRow.createCell(8);
            cell.setCellValue(registrationInfo.getBody().getItems().getRegistrationInfo()
                .getRegistrationRightInfo().getTerminationDate());
        }
    }

    private void createSheet1Header(Sheet sheet1) {
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
        headRowCell = headRow.createCell(6);
        headRowCell.setCellValue("expirationDate");
        headRowCell = headRow.createCell(7);
        headRowCell.setCellValue("terminationCauseName");
        headRowCell = headRow.createCell(8);
        headRowCell.setCellValue("terminationDate");
    }

    private Sheet createSheet(Workbook workbook, String name) {
        return workbook.createSheet(name);
    }
}
