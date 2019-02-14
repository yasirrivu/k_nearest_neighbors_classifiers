import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
public class Classifier {

	public static void main(String[] args) {
	 
		
		

		read_file f = new read_file(args[0]);
		read_file f3 = new read_file(args[1]);
		ArrayList<ArrayList<Double>> test = new ArrayList<>();
		ArrayList<ArrayList<Double>> train = new ArrayList<>();
		test=f.normalizedRowArray;
		train=f3.normalizedRowArray;
		int k=Integer.parseInt(args[2]);
		KNN d = new KNN(train , test , f3, f ,k);
		System.out.println(" "+"ID_NO"+"\t"+"Predicted Class"+"\t\t"+"Actual Class"+"\t");
		for(int i=0;i<d.final_class.size();i++)
		{
			System.out.println(" "+i+"\t\t"+d.final_class.get(i)+"\t\t"+f.rowArray.get(i).get(f.numberOfAttributes)+"\t");
		}
		}

}
		
		
	


