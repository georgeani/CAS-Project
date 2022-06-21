package logic;

import java.util.ArrayList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataManager {
	
	//file: The file that the DataManager will access
	private String file;
	
	public DataManager(String file){
		this.file = file;
	}
	
	//used to read a text file and fetch all the information
	public ArrayList<String> getData(){
		ArrayList<String> results = new ArrayList<>();
		BufferedReader readert = null;
		
		try {
			readert = new BufferedReader(new InputStreamReader(
	                new FileInputStream(file), StandardCharsets.UTF_8));
			String data = null;
			while((data = readert.readLine()) != null) {
				results.add(data);
			}
			readert.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
					if(readert != null) {
						readert.close();
					}
			} catch(IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
			}
		}
		
		return results;
	}
	
	//used to modify the data in the the text files
	public void modifyData(String dataToModify, String replasor) {
		ArrayList<String> contents = getData();		
		
		BufferedWriter writter = null;
		try {
			FileWriter foo = new FileWriter(file,false);
			writter = new BufferedWriter(foo);
			for(int i=0;i<contents.size();i++) {
				
				if(i==0 && contents.get(i).equals(dataToModify)) {
					writter.write(replasor);
				} else if (i==0) {
					writter.write(contents.get(i));
				}else if(contents.get(i).equals(null)) {
					
				}else if(contents.get(i).equals(dataToModify)) {
					writter.write("\n"+replasor);
				} else writter.write("\n"+contents.get(i));
					
			}
			writter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writter != null) {
					writter.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//used to remove data from the text files
	public void removeData(String dataToBeDeleted) {
		modifyData(dataToBeDeleted, "");
	}
	
	//used to add data at the top of the text files
	public void addData(String dataToBeAdded) {
		
		ArrayList<String> orig = getData();
		ArrayList<String> temp = new ArrayList<>();
		
		BufferedWriter writter = null;
		try {
			
			FileWriter foo = new FileWriter(file,false);
			writter = new BufferedWriter(foo);
			
			for (int i = -1; i < orig.size(); i++) {
				if(i==-1) {
					temp.add(dataToBeAdded);
				} else {
					temp.add(orig.get(i));
				}
			}
			
			for(int i=0;i<temp.size();i++) {
				if(i==0) {
					writter.write(temp.get(i));
				} else writter.write("\n"+temp.get(i));
				
			}
			writter.close();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(writter != null) {
					writter.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
		//used to add data at the end of the text files
		public void addDataEnd(String dataToBeAdded) {
			
			ArrayList<String> orig = getData();
			ArrayList<String> temp = new ArrayList<>();
			
			BufferedWriter writter = null;
			try {
				
				FileWriter foo = new FileWriter(file,false);
				writter = new BufferedWriter(foo);
				
				for (int i = 0; i < orig.size() + 1; i++) {
					if(i==orig.size()) {
						temp.add(dataToBeAdded);
					} else {
						temp.add(orig.get(i));
					}
				}
				
				for(int i=0;i<temp.size();i++) {
					if(i==0) {
						writter.write(temp.get(i));
					} else writter.write("\n"+temp.get(i));
					
				}
				writter.close();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(writter != null) {
						writter.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	
}
