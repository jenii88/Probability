import java.util.*;
import java.util.Formatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.io.*;

public class compute_a_posteriori
{
	public static void main(String[] args)throws IOException  {
		
		DecimalFormat df=new DecimalFormat("#.#####");   //formatting decimal into 5 digit place
		df.setRoundingMode(RoundingMode.CEILING);
		List<List<Double>> hypothesis = new ArrayList<List<Double>>();
		List<Double> prior = new ArrayList<Double>();
		
		prior.add(0.10);    //prior probability of individual bags
		prior.add(0.20);
		prior.add(0.40);
		prior.add(0.20);
		prior.add(0.10);
		hypothesis.add(prior);
		
		List<Double> CherryHypothesis = new ArrayList<Double>();
		CherryHypothesis.add(1.0);      //prior probability of Cherry
		CherryHypothesis.add(0.75);
		CherryHypothesis.add(0.50);
		CherryHypothesis.add(0.25);
		CherryHypothesis.add(0.0);
		
		List<Double> LimeHypothesis = new ArrayList<Double>();
		LimeHypothesis.add(0.0);
		LimeHypothesis.add(0.25);       //prior probability of Lime
		LimeHypothesis.add(0.50);
		LimeHypothesis.add(0.75);
		LimeHypothesis.add(1.0);
		
		
		String input=args[0];
		/*
		System.out.println("Enter the Observations");
		Scanner scanner=new Scanner(System.in);
		String input=scanner.nextLine();*/
		
		List<Character> observation = new ArrayList<Character>();
		
		for(int i=0; i<input.length(); i++)        //inserting input in an array
		{
			//System.out.println(input.charAt(i));
			observation.add(input.charAt(i));
		}
		
		
		for(int j=0; j<observation.size(); j++)
		{
			if(observation.get(j)=='C')     //If the observation is C compute probability likewise
			{
				double TotalCherry=(CherryHypothesis.get(0)*hypothesis.get(j).get(0)+
							CherryHypothesis.get(1)*hypothesis.get(j).get(1)+
							CherryHypothesis.get(2)*hypothesis.get(j).get(2)+
							CherryHypothesis.get(3)*hypothesis.get(j).get(3)+
							CherryHypothesis.get(4)*hypothesis.get(j).get(4));
				//System.out.println(TotalCherry);
			
				double knowncherry1=(CherryHypothesis.get(0)*hypothesis.get(j).get(0));
				double PossibleCherry1=(knowncherry1/TotalCherry);
				//System.out.println(PossibleCherry1);
				
				double knowncherry2=(CherryHypothesis.get(1)*hypothesis.get(j).get(1));
				double PossibleCherry2=(knowncherry2/TotalCherry);
				//System.out.println(PossibleCherry2);
				
				double knowncherry3=(CherryHypothesis.get(2)*hypothesis.get(j).get(2));
				double PossibleCherry3=(knowncherry3/TotalCherry);
				//System.out.println(PossibleCherry3);
							
				double knowncherry4=(CherryHypothesis.get(3)*hypothesis.get(j).get(3));
				double PossibleCherry4=(knowncherry4/TotalCherry);
				//System.out.println(PossibleCherry4);			
				double knowncherry5=(CherryHypothesis.get(4)*hypothesis.get(j).get(4));
				double PossibleCherry5=(knowncherry5/TotalCherry);
				//System.out.println(PossibleCherry5);				
				List<Double> temp = new ArrayList<Double>();
				double bam1=Double.parseDouble(df.format(PossibleCherry1));
				double bam2=Double.parseDouble(df.format(PossibleCherry2));
				double bam3=Double.parseDouble(df.format(PossibleCherry3));
				double bam4=Double.parseDouble(df.format(PossibleCherry4));
				double bam5=Double.parseDouble(df.format(PossibleCherry5));
				temp.add(bam1);
				temp.add(bam2);
				temp.add(bam3);
				temp.add(bam4);
				temp.add(bam5);
				hypothesis.add(temp);
			}
			
			else if(observation.get(j)=='L')    //If observation is L compute likewise
			{
				
				double TotalLime=(LimeHypothesis.get(0)*hypothesis.get(j).get(0)+
							 LimeHypothesis.get(1)*hypothesis.get(j).get(1)+
							LimeHypothesis.get(2)*hypothesis.get(j).get(2)+
							LimeHypothesis.get(3)*hypothesis.get(j).get(3)+
							LimeHypothesis.get(4)*hypothesis.get(j).get(4));
				//System.out.println(TotalLime);
				
				double knownlime1=(LimeHypothesis.get(0)*hypothesis.get(j).get(0));
				double PossibleLime1=(knownlime1/TotalLime);
				//System.out.println(PossibleLime1);
				
				double knownlime2=(LimeHypothesis.get(1)*hypothesis.get(j).get(1));
				double PossibleLime2=(knownlime2/TotalLime);
				//System.out.println(PossibleLime2);
				
				double knownlime3=(LimeHypothesis.get(2)*hypothesis.get(j).get(2));
				double PossibleLime3=(knownlime3/TotalLime);
				//System.out.println(PossibleLime3);
							
				double knownlime4=(LimeHypothesis.get(3)*hypothesis.get(j).get(3));
				double PossibleLime4=(knownlime4/TotalLime);
				//System.out.println(PossibleLime4);			
				double knownlime5=(LimeHypothesis.get(4)*hypothesis.get(j).get(4));
				double PossibleLime5=(knownlime5/TotalLime);
				//System.out.println(PossibleLime5);				
				List<Double> temp = new ArrayList<Double>();
				double sam1=Double.parseDouble(df.format(PossibleLime1));
				double sam2=Double.parseDouble(df.format(PossibleLime2));
				double sam3=Double.parseDouble(df.format(PossibleLime3));
				double sam4=Double.parseDouble(df.format(PossibleLime4));
				double sam5=Double.parseDouble(df.format(PossibleLime5));
				temp.add(sam1);
				temp.add(sam2);
				temp.add(sam3);
				temp.add(sam4);
				temp.add(sam5);
				hypothesis.add(temp);
			}
			
			else
			{
				System.out.println("Error in input");
			}
		}
		
		File fout = new File("result.txt");         //File output
		FileOutputStream fos = new FileOutputStream(fout);
		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("Observation sequence Q:");
		bw.write(input);
		bw.newLine();
		int come=input.length();
		String comestring=Integer.toString(come);
		bw.write("Length of Q:");
		bw.write(comestring);
		bw.newLine();

		for(int i=0;i<5;i++)     
		{
			double man1=hypothesis.get(hypothesis.size()-1).get(i);
			String x1=Double.toString(man1);
			String j=Integer.toString(i+1);
			bw.write("P(h");
			bw.write(j);
			 bw.write("| Q) = ");
			bw.write(x1);
			bw.newLine();
		}
		
		int x=hypothesis.size()-1;   //Calculating the probability of next observation 
		double FinalCherry=(CherryHypothesis.get(0)*hypothesis.get(x).get(0)+
							CherryHypothesis.get(1)*hypothesis.get(x).get(1)+
							CherryHypothesis.get(2)*hypothesis.get(x).get(2)+
							CherryHypothesis.get(3)*hypothesis.get(x).get(3)+
							CherryHypothesis.get(4)*hypothesis.get(x).get(4));
		double FinalLime=(LimeHypothesis.get(0)*hypothesis.get(x).get(0)+
						 LimeHypothesis.get(1)*hypothesis.get(x).get(1)+
						LimeHypothesis.get(2)*hypothesis.get(x).get(2)+
						LimeHypothesis.get(3)*hypothesis.get(x).get(3)+
						LimeHypothesis.get(4)*hypothesis.get(x).get(4));
		bw.write("Probability that the next candy we pick will be C, given Q:");
		double val=Double.parseDouble(df.format(FinalCherry));
		String Ch=Double.toString(val);
		bw.write(Ch);
		bw.newLine();
		bw.write("Probability that the next candy we pick will be L, given Q:");
		double val2=Double.parseDouble(df.format(FinalLime));
		String Lh=Double.toString(val2);
		bw.write(Lh);
		  
		bw.close();
		
		//System.out.println(hypothesis);
		}
}