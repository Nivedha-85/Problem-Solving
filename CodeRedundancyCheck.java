package crc;
import java.util.*;

public class CodeRedundancyCheck {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int dividend[] = new int[19];
		int divisor[] = new int[] {1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1};
		int remainder[] = new int[16];
		int dataword[] = new int[19];
		int codeword[] = new int[19];
		int r_s[] = new int[16];
		int n = 0;
		//System.out.println(dividend.length);
		System.out.println("Enter the size of the dividend");
		n = sc.nextInt();
		int[] originalCodeWord = new int[n];
		System.out.println("Enter the dividend");
		for(int i=0;i<n;i++)
		{
			dividend[i] = sc.nextInt();
			dataword[i] = dividend[i];
			codeword[i] = dividend[i];
			originalCodeWord[i] = dividend[i];
		}
		
		/*System.out.println("Enter the divisor");
		for(int j=0;j<divisor.length;j++)
		{
			divisor[j] = sc.nextInt();
		}*/
		
		int z = (divisor.length - 1);
		//int k = dividend.length;
		
		for(int b=0;b<z;b++) {
			dataword[n] = 0;
			n++;
		}
		
		System.out.println("The dataword is:");
		for(int h=0;h<dataword.length-1;h++)
		{
			System.out.print(dataword[h] + " ");
		}
		System.out.println();
		
		remainder = division(dataword,divisor);
		
		System.out.println("The remainder at sender site");
		for(int y=0;y<remainder.length-1;y++)
		{
			System.out.print(remainder[y]+ " ");
		}
		System.out.println();
		
		receiver(originalCodeWord,remainder,divisor);
		
		
		sc.close();
	}
	public static int[] division(int []dividend,int[]divisor) {
		int remainder[] = new int[17];
		int r1[] = new int[dividend.length];
		int r2[] = new int[divisor.length];
		int array[] = new int[17];
		for(int j=0;j<dividend.length;j++)
		{
			r1[j] = dividend[j];
		}
		for(int n=0;n<divisor.length;n++)
		{
			r2[n] = divisor[n];
			array[n] = 0;
		}
		// Based on divisor length
		for(int c=0;c<3;c++)
		{
			
			if(r1[0] == 1)
			{
				for(int v=0;v<divisor.length;v++)
				{
					remainder[v] = r1[v] ^ r2[v];
					
				}
				
				int v1;
				for(v1=1;v1<divisor.length;v1++)
				{	
					r1[v1-1] = remainder[v1] ;
				}
				if(c<16)
					r1[v1-1] = r1[v1+c];		
			}	
			else
			{	
				for(int v=0;v<divisor.length;v++)
				{
					remainder[v] = array[v] ^ r1[v];
				}
				
				int v1=0;
				for(v1=1;v1<divisor.length;v1++)
				{
					r1[v1-1] = remainder[v1] ;					
				}
				//if(c<16)
					//r1[v1-1] = r1[v1+c];
					
			}				
		}
		for(int u=0;u<remainder.length-1;u++)
		{
			remainder[u] = r1[u];
		}
		return remainder;
	}
	public static void receiver(int []originalCodeWord,int[] remainder,int [] divisor)
	{
		System.out.println(originalCodeWord.length + " " + remainder.length);
		int [] sendercodeword = new int[originalCodeWord.length + remainder.length];
		int initPoint = originalCodeWord.length;
		int i=0;
		for(i=0;i<originalCodeWord.length;i++)
		{
			sendercodeword[i] = originalCodeWord[i];
		}
		for(int j=0;j<remainder.length;j++)
		{
			sendercodeword[i] = remainder[j];
			i++;
			
		}
		
		//System.out.println(sendercodeword);
		System.out.println("The codeword is:");
		for(int u=0;u<sendercodeword.length;u++)
		{
			System.out.print(sendercodeword[u] + " ");
		}
		System.out.println();
		
		int r[] = new int[17];
		r = division(sendercodeword,divisor);
		
		System.out.println("The receiver remainder is:");
		for(int f=0;f<r.length;f++)
		{
			System.out.print(r[f] + " ");
		}
		System.out.println();
		int sum =0;
		for(int f=0;f<r.length;f++)
		{
			sum = sum + r[f];
		}
		if(sum == 0)
		{
			System.out.println("No syndrome,extract the dataword");
		}
		else
		{
			System.out.println("Syndrome,don't extract the dataword");
		}
	}

}


