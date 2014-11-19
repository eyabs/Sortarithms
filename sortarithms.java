package sortarithms;

import java.lang.Math;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

// Guava Import.
import com.google.common.base.Stopwatch;

public class sortarithms {	
	public static void main(String[] args){
		
		/// Declaring Lists to be sorted.
		
		// Uncomment for unsorted original list.
		//ArrayList<Double> randList = new ArrayList<Double>();		
		ArrayList<Double> BubbleSortList = new ArrayList<Double>();
		ArrayList<Double> SelectionSortList = new ArrayList<Double>();
		ArrayList<Double> QuickSort3List = new ArrayList<Double>();
		
		// Used for timing how fast the sorts work. 
		Stopwatch stopwatch = Stopwatch.createUnstarted();		
		long[][] trialTimes = new long[25][3];
		
		// Print the header.
		System.out.println("Trial:\tBubble Sort:\tSelection Sort:\tQuickSort3:");
		
		// Loop over 25 trials.
		for (int trial = 0; trial < 25; ++trial){
					
			// Fill the Lists.
			for (int loop = 0; loop < 10000; loop++ ){
				double val = Math.floor(getRandNum(0,100000));
				//randList.add(val);
				BubbleSortList.add(val);
				SelectionSortList.add(val);
				QuickSort3List.add(val);
			}
			
			// Do the Sort w/ Bubble Sort and time it.
			stopwatch.start();
			BubbleSort(BubbleSortList);
			stopwatch.stop();
			trialTimes[trial][0] = stopwatch.elapsed(TimeUnit.MICROSECONDS);
			stopwatch.reset();

			// Do the Sort w/ Selection Sort and time it.
			stopwatch.start();
			SelectionSort(SelectionSortList);			
			stopwatch.stop();
			trialTimes[trial][1] =stopwatch.elapsed(TimeUnit.MICROSECONDS);
			stopwatch.reset();
			
			// Do the sort w/ Quicksort3 and time it.
			stopwatch.start();
			quicksort3(QuickSort3List);
			stopwatch.stop();
			trialTimes[trial][2] =stopwatch.elapsed(TimeUnit.MICROSECONDS);
			stopwatch.reset();
						
			//Print the results
			System.out.println((trial+1) + "\t" + 
							trialTimes[trial][0] + "\t\t" + 
							trialTimes[trial][1] + "\t\t" + 
							trialTimes[trial][2] 
							);
			
			//if (trial == 24) for(double num : QuickSort3List) System.out.println(num);
			
			// Clear the lists.
			BubbleSortList.clear();
			SelectionSortList.clear();
			QuickSort3List.clear();		
		}	
	} // END OF MAIN()
	
	//  >>>----------------;;;;;----->
	
	//double getRandNum(int aMin, int aMax)
	//generates a random double between aMin and aMax
	public static double getRandNum(int aMin, int aMax)	{
		return Math.random() * ((double)aMax - (double)aMin) + (double)aMin;
	}

	// Bubble Sort (list)
	// Sorts list.... slowly
	public static void BubbleSort(ArrayList<Double> aList){
		
		int stop = aList.size() - 1;
		boolean sorted;		
		
		// Swap Adjacent Pairs until sorted.
		do{
			sorted = true;
			for (int loop = 0; loop < stop; loop++){
				if (aList.get(loop) > aList.get(loop + 1)){
					swap(aList, loop, loop+1);
					sorted = false;
				}
			}
		}while(!sorted);
	}
	
	// SelectionSort(list)
	// Sort, a little faster than bubblesort
	public static void SelectionSort(ArrayList<Double> aList){
		int index;
		int stop = aList.size();
		
		// insert n-th smallest into n-th position, iterate n
		for (int rank = 0; rank < stop; ++rank){
			index = rank;
			for (int record = index; record < stop; record++){
				if (aList.get(record) < aList.get(index)){
					swap(aList,index,record);					
				}
			}
		}
		
	}
		
	// quicksort3( list )
	// calls quicksort3 ( list, left, right )
	public static void quicksort3(ArrayList<Double> aList){	quicksort3(aList, 0, aList.size() - 1);	}
	
	// quicksort3 ( list, left, right )
	// fastest of the three, BY FAR. Works by recursively partitioning by big/small around a pivot point.  
	public static void quicksort3(ArrayList<Double> aList, int aLeft, int aRight){
		if (aLeft < aRight){
			int pivot = partition3(aList, aLeft, aRight);
			quicksort3(aList, aLeft, pivot-1);
			quicksort3(aList, pivot+1 , aRight);
		}
	}
	
	// partition3 (list, left, right)
	// Does the partitioning for quicksort3
	private static int partition3(ArrayList<Double> aList, int aLeft, int aRight){
		int middle = aLeft + (aRight-aLeft)/ 2;
		int pivot = median(aList, aLeft, middle, aRight);
		double pivotValue = aList.get(pivot);		
		swap(aList, pivot, aRight);
		int index = aLeft;
		
		for (int loop = aLeft; loop < aRight ; ++loop){
			if (aList.get(loop) < pivotValue){
				swap(aList,loop,index);
				index+=1;
			}
		}
		swap(aList,index,aRight);
		return index;
		
		
	}	
	
	// Swaps two values of a list
	private static void swap(ArrayList<Double> aList, int firstValIndex, int secondValIndex){
		double temp = aList.get(firstValIndex);
		aList.set(firstValIndex, aList.get(secondValIndex));
		aList.set(secondValIndex, temp);
		
	}
	
	// Finds the median of three values in a list
	private static int median(ArrayList<Double> aList, int a, int b, int c){
		double valA = aList.get(a);
		double valB = aList.get(b);
		double valC = aList.get(c);
		
		if (valA >= valB && valA >= valC) {
			if (valB >= valC) return b;
			else return c;
		}
		else if (valA < valB && valA < valC ) {
			if (valB < valC) return b;
			else return c;
		}
		else return a;
	}
	
}

