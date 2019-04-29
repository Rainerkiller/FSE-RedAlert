package com.mygdx.game.desktop;

public class LTest {
    public static void main(String[] args){
        LList nums = new LList();
        nums.enque(100);
        nums.enque(90);
        nums.enque(80);
        nums.enque(80);
        nums.enque(80);
        nums.push(80);
        //nums.push(80);
        //nums.push(70);
        //nums.enque(50);
        //nums.pop();
        //nums.push(20);

        System.out.println(nums);
        //nums.deque();
        //nums.sort(85);
        //nums.removeDuplicates();
        //nums.deleteAt(1);
        //nums.reserve();
        nums.reverse();
        LList m = nums.clone();
        System.out.println(m);
    }
}
