import java.lang.Math;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.Scanner;

public class read_file {
 	private String trainingFile ;
	public ArrayList<ArrayList<Double>> rowArray = new ArrayList<>();
	
	public ArrayList<ArrayList<Double>> colArray = new ArrayList<>();
	public ArrayList<ArrayList<Double>> normalizedColArray = new ArrayList<>();
	public ArrayList<ArrayList<Double>> normalizedRowArray = new ArrayList<>();
	public ArrayList<Double> meanColArray = new ArrayList<>();
	public int numberOfAttributes;
	public int numberOfClass;
	public ArrayList<Double> StdcolArray = new ArrayList<>();
	
	
	public read_file(String fileName) {
		// TODO Auto-generated constructor stub
		this.trainingFile=fileName;
		processData(trainingFile);
		//normalizeAttributes();
		calc_Mean();
		calc_StdDev();
		calc_Normalize();
		assign_NormalizeRow();
	}
	public void  calc_Normalize()
	{
		ArrayList<Double> normalColArray = new ArrayList<>();
		Double t=0.0;
		int k = colArray.get(0).size();
		for (int  i = 0; i <( colArray.size() - 1); i++)
			{for (int  j = 0; j < k ; j++)
				{t=(colArray.get(i).get(j)-meanColArray.get(i))/StdcolArray.get(i);
				normalColArray.add(t);
				}
			normalizedColArray.add(normalColArray);
			normalColArray = new ArrayList<>();
				}
	}
	
	public void  assign_NormalizeRow()
	{
		int k = colArray.size();
		ArrayList<Double> normalRowArray = new ArrayList<>();
		int j = colArray.get(0).size();
		for(int i=0;i<j;i++)
		{
			for(int l=0;l <numberOfAttributes;l++)
			{	normalRowArray.add(normalizedColArray.get(l).get(i) );
			}
			normalizedRowArray.add(normalRowArray);
			normalRowArray = new ArrayList<>();
			}
		
		}
		
	
	public void  calc_Mean() {
		Double t=0.0;
		int k = colArray.get(0).size();
		for (int  i = 0; i <( colArray.size() - 1); i++)
			{for (int  j = 0; j < k ; j++)
				{t=t+colArray.get(i).get(j);}
		Double m=t/k;
		meanColArray.add(m);
		t=0.0;
			}
	}
	public void  calc_StdDev() {
		Double t=0.0;
		int k = colArray.get(0).size();;
		for (int  i = 0; i <( colArray.size() - 1); i++)
			{for (int  j = 0; j < k ; j++)
				{
				t=t+Math.pow((colArray.get(i).get(j)- meanColArray.get(i)),2);
				
				}
		Double m=t/(k-1);
		m=Math.sqrt(m);
		t=0.0;
		StdcolArray.add(m);
		t=0.0;
			}
				
			}
	
	
	

	public ArrayList<Integer>  getAttributes() {
		ArrayList<Integer> attributes = new ArrayList <>();
		for (int  i = 0; i < numberOfAttributes; i++)
			attributes.add(i);
		return attributes;
	}
	
	
	
	
	public ArrayList<ArrayList<Double>> getRowArray() {
		return rowArray;
	}
	
	public ArrayList<ArrayList<Double>> getColArray() {
		return colArray;
	}
	
	
	
	public void processData(String fileName) {
		String line="";
	    FileReader fr;
	    int i=0, j=0;
	
		try {
			fr = new FileReader(fileName);
	        Scanner scan = new Scanner (fr);
	        while(scan.hasNext()){
	        	line=scan.nextLine();
	        	Scanner sc = new Scanner (line);
	        	ArrayList<Double> row = new ArrayList<>();
		        while(sc.hasNext()){
		        	row.add(sc.nextDouble());
		        }
		        rowArray.add(row);
	        	sc.close();
	        }
	        scan.close();
	        //rowArray list is done. using it to create column arraylist
	        
	        int row= 0;
	        int col=0;
	        int rowSize = rowArray.size();
	        if (rowSize>0){
		        row= rowArray.size();
		        col=rowArray.get(0).size();
		        numberOfAttributes=col-1;
		        if (numberOfAttributes == 16)
		        	numberOfClass= 10;
		        else if (numberOfAttributes== 36)
		        	numberOfClass = 6;
		        else if (numberOfAttributes== 8)
		        	numberOfClass = 10;
		        	
		        
		        
				//System.out.println("column = " + col+ "row = "+row);
				for ( i=0;i<col;i++){
				
				    ArrayList<Double> column = new ArrayList<>();
				    
				    for(j=0;j<row;j++) {
				    	column.add(rowArray.get(j).get(i));	
			    	}
				    colArray.add(column);
				}
	        }
	    
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	

}
/*


 */