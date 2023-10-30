package com.emsi.javafx.service;


import com.emsi.javafx.ImportExportToDataBase.DataExcel;
import com.emsi.javafx.ImportExportToDataBase.DataTxt;

public class DataServiceImpl extends CompteService {

    private DataExcel dataExcel;
	private DataTxt dataTxt;
	
    public DataServiceImpl() {
        dataExcel = new DataExcel();
        dataTxt = new DataTxt();
    }

    public void importFromExcel(String filePath) {
		dataExcel.importDataFromExcel(filePath);
	}

    public void exportToExcel(String filePath) {
        dataExcel.exportDataToExcel(filePath);
    }
    
    public void importFromTextFile(String filePath) {
		dataTxt.importDataFromTextFile(filePath);
	}

	public void exportToTextFile(String filePath) {
		dataTxt.exportDataToTextFile(filePath);
	}

}