package com.xiao.studydemos.sorts;

import java.util.Arrays;

/**
 * Created by xiaoliang on 2016/10/4.
 * 冒泡排序是非常容易理解和实现，，以从小到大排序举例：
 设数组长度为N。

 1．比较相邻的前后二个数据，如果前面数据大于后面的数据，就将二个数据交换。

 2．这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1个位置。

 3．N=N-1，如果N不为0就重复前面二步，否则排序完成。
 */
public class BubbleSort {

    private int[] datas = Constants.SORT_ARR;

    public void sort(){
        int i =0 ,j = 0;
        System.out.println(Arrays.toString(datas));

        for(i = datas.length-1;i>0;i--){
            for(j=0;j<i;j++){
                this.swap(datas,j,j+1);
                System.out.println(Arrays.toString(datas));
            }
        }

        System.out.println(Arrays.toString(datas));
    }

    public void sort2(){
        for(int i = datas.length-1;i>0;i--){
            boolean isSwap = false;
            for(int j = 0; j<i;j++){
                if(datas[j] > datas[j+1]){
                    int tmp = datas[j];
                    datas[j] = datas[j+1];
                    datas[j+1] = tmp;
                    isSwap = true;
                }
            }
            System.out.println(Arrays.toString(datas));
            if(!isSwap){
                break;
            }
        }
        System.out.println(Arrays.toString(datas));
    }

    private boolean swap(int[] arr,int left,int right){
        if(arr[left] > arr[right]){
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            return true;
        }
        return false;
    }
}
