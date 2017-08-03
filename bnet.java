import java.util.*;
import java.util.Formatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.io.*;

class bnet {
	public static void main(String[] args) {
		
		network call=new network();         //instantiating the class 
		DecimalFormat df=new DecimalFormat("#.#####");
		df.setRoundingMode(RoundingMode.CEILING);
		List<String> input = new ArrayList<String>();
		List<String> given_input=new ArrayList<String>();
		for (int i=0;i<args.length;i++)
		{
			if(args[i].equals("given"))
			{
				i=i+1;
				while(i!=args.length)
				{
					given_input.add(args[i]);
					i=i+1;
				}
				break;
			}
			
			else
			{
				input.add(args[i]);
			}
		}
		
		for(int j=0;j<given_input.size();j++)
		{
			input.add(given_input.get(j));
		}
		System.out.print("input parameters:");
		System.out.println(input);
		System.out.print("conditional input parameters:");
		System.out.println(given_input);
		
		Boolean burglary= null;
		Boolean earthquake=null;
		Boolean alarm=null;
		Boolean john=null;
		Boolean mary=null;
		
		Boolean burglary_1= null;
		Boolean earthquake_1=null;
		Boolean alarm_1=null;
		Boolean john_1=null;
		Boolean mary_1=null;
		
		for(int r=0;r<input.size();r++)     //assigning the boolean value to the input
		{
			if(input.get(r).equals("Bt"))
			{
				burglary=true;
			}
			
			else if(input.get(r).equals("Bf"))
			{
				burglary=false;
			}
			
			else if(input.get(r).equals("Et"))
			{
				earthquake=true;
			}
			
			else if(input.get(r).equals("Ef"))
			{
				earthquake=false;
			}
			
			else if(input.get(r).equals("At"))
			{
				alarm=true;
			}
			
			else if(input.get(r).equals("Af"))
			{
				alarm=false;
			}
	
			else if(input.get(r).equals("Jt"))
			{
				john=true;
			}
			
			else if(input.get(r).equals("Jf"))
			{
				john=false;
			}

			else if(input.get(r).equals("Mt"))
			{
				mary=true;
			}
			
			else if(input.get(r).equals("Mf"))
			{
				mary=false;
			}
			
		}
		
		for(int p=0;p<given_input.size();p++)
		{
			
			if(given_input.get(p).equals("Bt"))
			{
				burglary_1=true;
				//System.out.println(burglary_1);
			}
			
			else if(given_input.get(p).equals("Bf"))
			{
				burglary_1=false;
			}
			
			else if(given_input.get(p).equals("Et"))
			{
				earthquake_1=true;
			}
			
			else if(given_input.get(p).equals("Ef"))
			{
				earthquake_1=false;
			}
			
			else if(given_input.get(p).equals("At"))
			{
				alarm_1=true;
			}
			
			else if(given_input.get(p).equals("Af"))
			{
				alarm_1=false;
			}
	
			else if(given_input.get(p).equals("Jt"))
			{
				john_1=true;
			}
			
			else if(given_input.get(p).equals("Jf"))
			{
				john_1=false;
			}

			else if(given_input.get(p).equals("Mt"))
			{
				mary_1=true;
			}
			
			else if(given_input.get(p).equals("Mf"))
			{
				mary_1=false;
			}
			
		}
		
		if(given_input.size()!=0)
		{
			double value=call.computeProbability(burglary, earthquake, alarm, john, mary);
			double value_1=call.computeProbability(burglary_1, earthquake_1, alarm_1, john_1, mary_1);
			//System.out.print("Probability:")
			//System.out.println(value);
			//System.out.println(value_1);
			double totalvalue=value/value_1;
			System.out.print("Probability:");
			System.out.println(totalvalue);
		}
		else {
			double value=call.computeProbability(burglary, earthquake, alarm, john, mary);
			System.out.print("Probability:");
			System.out.println(value);
		}
		
	}
}

class network
{
	public double computeProbability(Boolean b, Boolean e, Boolean a, Boolean j, Boolean m)
	
	{
		DecimalFormat df=new DecimalFormat("#.#####");
		df.setRoundingMode(RoundingMode.CEILING);
		Boolean o = null;
		//Double.parseDouble(df.format(PossibleLime5));
		// value in [0] position is false value and [1] is true value 
		double[] burglary = {0.999, 0.001}; //F,T
		
		double[] earthquake ={0.998, 0.002}; //F,T
		
		double[] alarm_given_BE={0.001, 0.29, 0.94, 0.95}; //FF,FT,TF,TT
		
		double[] johnCall_given_A={0.05, 0.90}; //F,T
		
		double[] maryCall_given_A={0.01, 0.70}; //F,T
		
		if(b==null)
		{
			double burger1=computeProbability(true, e, a , j, m);
			double burger2=computeProbability(false, e, a , j , m);
			//double X=Double.parseDouble(df.format(burger1+burger2));
			//return X;
			return burger1+burger2;
		}
		
		if(e==null)
		{
			double earth1=computeProbability(b, true, a , j, m);
			double earth2=computeProbability(b, false, a , j , m);
			//double Y=Double.parseDouble(df.format(earth1+earth2));
			//return Y;
			return earth1+earth2;
		}
		
		if(a==null)
		{
			double alarm1=computeProbability(b, e, true, j, m);
			double alarm2=computeProbability(b, e, false, j, m);
			//double Y=Double.parseDouble(df.format(alarm1+alarm2));
			//return Y;
			return alarm1+alarm2;
		}

		
		if((b==true) && (e==true))
		
		{
			//System.out.println("asklgjlsd");
			//if((a==true) && (j!=null) && (m!=null))
			if((a==true) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				
				double ARtrue=(burglary[1]*earthquake[1]*alarm_given_BE[3]*johnCall_given_A[1]*maryCall_given_A[1]);
				//return Double.parseDouble(df.format(ARtrue));
				return ARtrue;
			}
			
			else if((a==false) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				double ARfalse=(burglary[1]*earthquake[1]*(1-alarm_given_BE[3])*johnCall_given_A[0]*maryCall_given_A[0]);
				//return Double.parseDouble(df.format(ARfalse));
				return ARfalse;
			}
			
			else if((a==true) && (j==null) && (m==null))
			{
			
				double ARtrue_only=(burglary[1]*earthquake[1]*alarm_given_BE[3]);
				//return Double.parseDouble(df.format(ARtrue_only));
				return ARtrue_only;
						
			}
			
			else if((a==false) && (j==null) && (m==null))
			{
			
				double ARtrue_only1=(burglary[1]*earthquake[1]*(1-alarm_given_BE[3]));
				//return Double.parseDouble(df.format(ARtrue_only1));
				return ARtrue_only1;
						
			}
			
		}
		
		else if((b==true) && (e==false))
		{
			
			//if((a==true) && (j!=null) && (m!=null))
			if((a==true) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				
				double AR_one= (burglary[1]*earthquake[0]*alarm_given_BE[2]*johnCall_given_A[1]*maryCall_given_A[1]);
				//return Double.parseDouble(df.format(AR_one));
				return AR_one;
			}
			else if((a==false) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				double AR_two= (burglary[1]*earthquake[0]*(1-alarm_given_BE[2])*johnCall_given_A[0]*maryCall_given_A[0]);
				//return Double.parseDouble(df.format(AR_two));
				return AR_two;
			}
			
			else if((a==true) && (j==null) && (m==null))
			{
				double AR_one= (burglary[1]*earthquake[0]*alarm_given_BE[2]);
				//return Double.parseDouble(df.format(AR_one));
				return AR_one;
			}
			else if((a==false) && (j==null) && (m==null))
			{
				double AR_two= (burglary[1]*earthquake[0]*(1-alarm_given_BE[2]));
				//return Double.parseDouble(df.format(AR_two));
				return AR_two;
			}
			
			
		}


		else if((b==false) && (e==true))
		{
			if((a==true) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				double AR_three=(burglary[0]*earthquake[1]*alarm_given_BE[1]*johnCall_given_A[1]*maryCall_given_A[1]);
				//return Double.parseDouble(df.format(AR_three));
				return AR_three;
			}
			
			else if((a==false) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				double AR_four=(burglary[0]*earthquake[1]*(1-alarm_given_BE[1])*johnCall_given_A[0]*maryCall_given_A[0]);
				//return Double.parseDouble(df.format(AR_four));
				return AR_four;
			}
			
			else if((a==true) && (j==null) && (m==null))
			{
				double AR_three=(burglary[0]*earthquake[1]*alarm_given_BE[1]);
				//return Double.parseDouble(df.format(AR_three));
				return AR_three;
			}
			
			else if((a==false) && (j==null) && (m==null))
			{
				double AR_four=(burglary[0]*earthquake[1]*(1-alarm_given_BE[1]));
				//return Double.parseDouble(df.format(AR_four));
				return AR_four;
			}
			
		}
		
		else if((b==false) && (e==false))
		{
			if((a==true) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m)))
			{
				double AR_five=(burglary[0]*earthquake[0]*alarm_given_BE[0]*johnCall_given_A[1]*maryCall_given_A[1]);
				//return Double.parseDouble(df.format(AR_five));
				return AR_five;
			}
			else if((a==false) && (Boolean.TRUE.equals(j) || Boolean.FALSE.equals(j)) && (Boolean.TRUE.equals(m) || Boolean.FALSE.equals(m))) 
			{
				double AR_six=(burglary[0]*earthquake[0]*(1-alarm_given_BE[0])*johnCall_given_A[0]*maryCall_given_A[0]);
				//return Double.parseDouble(df.format(AR_six));
				return AR_six;

			}
			
			else if((a==true) && (j==null) && (m==null))
			{
				double AR_five=(burglary[0]*earthquake[0]*alarm_given_BE[0]);
				//return Double.parseDouble(df.format(AR_five));
				return AR_five;
			}
			
			else if((a==false) && (j==null) && (m==null))
			{
				double AR_six=(burglary[0]*earthquake[0]*(1-alarm_given_BE[0]));
				//return Double.parseDouble(df.format(AR_six));
				return AR_six;

			}
			
		}

		return 0;
}

}
