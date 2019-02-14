import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.lang.Math;
public class KNN {
 
	public read_file f3 = new read_file("pendigits_training.txt");
	public read_file f = new read_file("pendigits_training.txt");
	public int h;
	//public ArrayList<ArrayList<Double>> normalizedColArray_train = new ArrayList<>();
	public ArrayList<ArrayList<Double>> normalizedRowArray_train = new ArrayList<>();
	public ArrayList<Double> final_class =  new ArrayList<>();
	//public ArrayList<ArrayList<Double>> normalizedColArray_test = new ArrayList<>();
	  public ArrayList<ArrayList<Double>> normalizedRowArray_test = new ArrayList<>();
	
	public ArrayList<ArrayList<Double>> Distance_test_train = new ArrayList<>();
	public ArrayList<ArrayList<Double>> SortDistance_test_train = new ArrayList<>();
	public ArrayList<ArrayList<Double>> near_neighbors = new ArrayList<>();
	public ArrayList<ArrayList<Double>> listOfClass_test = new ArrayList<>();
	public ArrayList<ArrayList<Double>> SortlistOfClass_test = new ArrayList<>();
	public KNN(ArrayList<ArrayList<Double>> train,ArrayList<ArrayList<Double>> test, read_file file, read_file file_test, int k ) {
		// TODO Auto-generated constructor stub
		this.normalizedRowArray_test = test;
		this.normalizedRowArray_train = train;
		this.f3 = file;
		this.f = file_test;
		this.h=k;
		
		CalcDistance();
		SortDistance();
		Get_near_neighbor();
		Get_listOfClass_test();
		Get_SortlistOfClass_test();
		Get_Class();
		
			}
	private void  Get_listOfClass_test() {
		ArrayList<Double> Class = new ArrayList<>();
		Double m=0.0;
		for (int j=0;j<Distance_test_train.size();j++)
		{
		for (int i=0;i<f3.numberOfClass;i++)
		{
			for (int l=0;l<h;l++)
			{
				if((i == near_neighbors.get(j).get(l)))
					m=m+1;
			}
			Class.add(m);
			m=0.0;
			
		}
		
		listOfClass_test.add(Class);  
		Class = new ArrayList<>();	
		}
	}
	private void  Get_SortlistOfClass_test() {
		ArrayList<Double> list = new ArrayList<>();
		
		for(int i=0;i<listOfClass_test.size();i++)
		{for(int j=0;j<listOfClass_test.get(0).size();j++)
			{list.add(listOfClass_test.get(i).get(j)) ;
			
			}
		SortlistOfClass_test.add(list);
		
		list = new ArrayList<>();
		}
		
		for(int i=0;i<listOfClass_test.size() ;i++)
			 Collections.sort(SortlistOfClass_test.get(i));
		

	}
	private void  Get_Class() {
		 for (int j=0;j<SortlistOfClass_test.size();j++)
			{
				double k = 	listOfClass_test.get(j).indexOf(SortlistOfClass_test.get(j).get(f.numberOfClass - 1));
				final_class.add(k);
				
				}
		
	}
	private void  Get_near_neighbor() {
		ArrayList<Double> near = new ArrayList<>();
		
		for (int j=0;j<Distance_test_train.size();j++)
		{
		for (int i=0;i<h;i++)
			{
			int k = 	Distance_test_train.get(j).indexOf(SortDistance_test_train.get(j).get(i));
			near.add(f3.rowArray.get(k).get(f3.numberOfAttributes)) ;
			}
		
		near_neighbors.add(near);
		near = new ArrayList<>();
		}
	}
	
	private void  CalcDistance() {
		Double t=0.0;
		ArrayList<Double> distance = new ArrayList<>();
		int k =normalizedRowArray_test.size();
		int m =normalizedRowArray_train.size();
		int l =normalizedRowArray_test.get(0).size();
		for (int  i = 0; i <k; i++)
		{
			for (int  j = 0; j < m ; j++)
			{
				for (int  n = 0; n < l ; n++)
				{
					double y=(normalizedRowArray_test.get(i).get(n)-normalizedRowArray_train.get(j).get(n));
					double z=y*y;
					t=t+z;
					//System.out.println(t);
				}
				//System.out.println(t);
				
				distance.add(Math.sqrt(t));
				t=0.0;
				
				
			
				
			}  
			
			Distance_test_train.add(distance);
			distance = new ArrayList<>();
			}
		
		
						
			}
	
	public void  SortDistance() {
		ArrayList<Double> distance = new ArrayList<>();
		for(int i=0;i<Distance_test_train.size();i++)
		{for(int j=0;j<Distance_test_train.get(0).size();j++)
			{distance.add(Distance_test_train.get(i).get(j)) ;
			
			}
		SortDistance_test_train.add(distance);
		
		distance = new ArrayList<>();
		}
		
		
		 for(int i=0;i<SortDistance_test_train.size() ;i++)
			 Collections.sort(SortDistance_test_train.get(i));
		
		
	}
	
	
	
	
}
