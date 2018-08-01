package org.gustini.library.excelGenerator;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * Gustini GmbH (2015)
 * Creation: 19.01.2015
 * Rentasad Library
 * rentasad.lib.tools.excelGenerator
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 * Excelgenerator, der auf Basis der jxl.Workbook-API Exceldokumente erzeugt. (XLS, kein XLSX)
 * Es werden vereinfach Exceldokumente auf Basis von implementierten Interfaces generiert.
 * @deprecated 
 * Bitte den XLSXGenerator verwenden
 */
public class GenerateExcel
{
	
	
	/**
	 * 
	 * @param fileName Dateiname der Excel-Datei
	 * @param sheetName Name des Tabellenblattes
	 * @param iExcelMatrixs DatenobjektArray
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws IOException 
	 */
	public static void generateExcelFromExcelMatrixClass(String fileName, String sheetName, IExcelMatrix[] iExcelMatrixs ) throws RowsExceededException, WriteException, IOException
	{
		File file = new File(fileName);
		WritableWorkbook workbook;
		

		workbook = Workbook.createWorkbook(file);
		
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			int zeile = 0;
			for (int i = 0; i < iExcelMatrixs.length; i++)
			{
				// Der erste Eintrag wird verwendet, um den Tabellenkopf zu erstellen				

				IExcelMatrix dataObject = iExcelMatrixs[i];
				ExcelGeneratorValueEntry[] entries = dataObject.getExcelGeneratorValueEntries();
				if (zeile==0)
				{
					for (int spalte = 0; spalte < entries.length; spalte++)
					{
						ExcelGeneratorValueEntry entry = entries[spalte];
						Label headerLabel = new Label(spalte,zeile,entry.getColumnName());
						sheet.addCell(headerLabel);
					}

					// Header komplett geschrieben, Zeile rutschen:
					zeile++;
				}
				
				for (int spalte = 0; spalte < entries.length; spalte++)
				{
					
					ExcelGeneratorValueEntry entry = entries[spalte];
					/*
					 * Je nach Datentyp wird eine entsprechende Zelle dem "Worksheet" hinzugefuegt.
					 */
					switch (entry.getDataType())
					{
					case ExcelGeneratorValueEntry.CELL_TYPE_DATE:
						DateTime valueDateTime = new DateTime(spalte, zeile, entry.getDateValue());
						sheet.addCell(valueDateTime);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_LABEL:
						Label valueLabel = new Label(spalte, zeile, entry.getStringValue());
						sheet.addCell(valueLabel);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_DOUBLE:
						Number numberValueDouble = new Number(spalte, zeile, entry.getDoubleValue());
						sheet.addCell(numberValueDouble);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_FLOAT:
						Number numberValueFloat = new Number(spalte, zeile, entry.getFloatValue());
						sheet.addCell(numberValueFloat);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_INT:
						Number numberValueInt = new Number(spalte, zeile, entry.getIntValue());
						sheet.addCell(numberValueInt);
						break;
					default:
						System.err.println("Falscher Datentyp!!!!!");
						break;
					}
					

					
				}
				zeile++;
			}
			workbook.write();
			workbook.close();
	}
	
	
	
	/**
	 * 
	 * @param fileName Dateiname der Excel-Datei
	 * @param sheetName Name des Tabellenblattes
	 * @param iExcelMatrixs DatenobjektArray
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws IOException 
	 */
	public static WritableWorkbook addWritableSheetFromExcelMatrixClass(WritableWorkbook workbook, String sheetName, IExcelMatrix[] iExcelMatrixs ) throws RowsExceededException, WriteException
	{
		
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			int zeile = 0;
			for (int i = 0; i < iExcelMatrixs.length; i++)
			{
				// Der erste Eintrag wird verwendet, um den Tabellenkopf zu erstellen				

				IExcelMatrix dataObject = iExcelMatrixs[i];
				ExcelGeneratorValueEntry[] entries = dataObject.getExcelGeneratorValueEntries();
				if (zeile==0)
				{
					for (int spalte = 0; spalte < entries.length; spalte++)
					{
						ExcelGeneratorValueEntry entry = entries[spalte];
						Label headerLabel = new Label(spalte,zeile,entry.getColumnName());
						sheet.addCell(headerLabel);
					}

					// Header komplett geschrieben, Zeile rutschen:
					zeile++;
				}
				
				for (int spalte = 0; spalte < entries.length; spalte++)
				{
					
					ExcelGeneratorValueEntry entry = entries[spalte];
					/*
					 * Je nach Datentyp wird eine entsprechende Zelle dem "Worksheet" hinzugefuegt.
					 */
					switch (entry.getDataType())
					{
					case ExcelGeneratorValueEntry.CELL_TYPE_DATE:
						DateTime valueDateTime = new DateTime(spalte, zeile, entry.getDateValue());
						sheet.addCell(valueDateTime);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_LABEL:
						Label valueLabel = new Label(spalte, zeile, entry.getStringValue());
						sheet.addCell(valueLabel);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_DOUBLE:
						Number numberValueDouble = new Number(spalte, zeile, entry.getDoubleValue());
						sheet.addCell(numberValueDouble);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_FLOAT:
						Number numberValueFloat = new Number(spalte, zeile, entry.getFloatValue());
						sheet.addCell(numberValueFloat);
						break;
					case ExcelGeneratorValueEntry.CELL_TYPE_NUMBER_INT:
						Number numberValueInt = new Number(spalte, zeile, entry.getIntValue());
						sheet.addCell(numberValueInt);
						break;
					default:
						System.err.println("Falscher Datentyp!!!!!");
						break;
					}
				}
				zeile++;
			}
			return workbook;
	}
	
	
}
