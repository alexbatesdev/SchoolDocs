﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures
{
    public class AVLTree<T> where T : IComparable<T>
    {
        public AVLTree() { }
        public AVLTree(T initialValue)
        {
            Root = new AVLTreeNode<T>(initialValue);
        }

        public AVLTreeNode<T> Root;
        public int Count { get; private set; }

        public void Add(T value)
        {
            if (Root == null)
            {
                Root = new AVLTreeNode<T>(value);
                Count++;
            }
            else
            {
                Root = Root.Add(value);
                Count++;
            }
        }

        public void Remove(T value)
        {
            if (Root != null)
            {
                Root = Root.Remove(value);
                Count--;
            }
        }

        public Boolean Contains(T value)
        {
            if (Root != null)
            {
                return Root.Contains(value);
            }
            else
            {
                return false;
            }
        }

        public void Clear()
        {
            Root = null;
            Count = 0;
        }

        public int Height()
        {
            //I have no clue how to do this one
            //Iterate down the tree, somehow avoid or turn around after short branches, counting how many steps the biggest branch is
            
            // The way I did it is bad, look into Beardall's solution
            // Having to pass in these variable references sucks
            int output = 0;
            if (Root != null)
            {
                output = Root.Height();
            }
            else
            {
                return 0;
            }

            return output;
        }

        public T[] ToArray()
        {
            List<T> array = new List<T>();
            if (Root != null)
            {
                for (int i = 0; i < this.Height(); i++)
                {
                    Root.ToArray(array, i, i);
                }
            }
            else
            {
                return new T[0];
            }
            return array.ToArray();
        }

        public string InOrder()
        {
            List<T> array = new List<T>(); //instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.InOrder(array);
            }
            else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output;
        }

        public string PreOrder()
        {
            List<T> array = new List<T>();//instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.PreOrder(array);
            }
            else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output;
        }

        public string PostOrder()
        {
            List<T> array = new List<T>();//instead of a List I could just pass a string with the ref keyword so it isn't passed by value
            string output = "";
            if (Root != null)
            {
                Root.PostOrder(array);
            }
            else
            {
                return string.Empty;
            }

            for (int i = 0; i < array.Count; i++)
            {
                output += array[i].ToString();
                if (i < array.Count - 1) output += ", ";
            }

            return output;
        }
    }

    public class AVLTreeNode<T> where T : IComparable<T>
    {
        public AVLTreeNode(T value)
        {
            this.Value = value;
        }

        public T Value;
        public AVLTreeNode<T> Left;
        public AVLTreeNode<T> Right;

        public AVLTreeNode<T> Add(T value)
        {
            if (value.CompareTo(Value) < 0)
            {
                if (Left == null)
                {
                    Left = new AVLTreeNode<T>(value);
                }
                else
                {
                    Left = Left.Add(value);
                }
            }
            else if (value.CompareTo(Value) > 0)
            {
                if (Right == null)
                {
                    Right = new AVLTreeNode<T>(value);
                }
                else
                {
                    Right = Right.Add(value);
                }
            }
            else
            {
                //If value is a duplicate, add to the right
                if (Right == null)
                {
                    Right = new AVLTreeNode<T>(value);
                }
                else
                {
                    Right.Add(value);
                }
            }
            return BalanceTree();
        }

        public AVLTreeNode<T> Remove(T value)
        {
            //No children
            // Tell Parent to null pointer
            //One child
            // Tell Parent to assign child to Left or Right
            //Two children
            //swap the values around, no need for tons of node movements
            //Traverse the tree depending on the value
            if (value.CompareTo(this.Value) < 0)
            {
                Left = Left.Remove(value);
            }
            else if (value.CompareTo(this.Value) > 0)
            {
                Right = Right.Remove(value);
            }
            else
            {
                //If the right is null, then the left is our child
                if (Right == null)
                {
                    return Left;
                }
                else if (Left == null) //If the left is null, then the right is our child
                {
                    return Right;
                }
                else // if neither are null we have a double child scenario
                {
                    //Here we grab the largest number from the lesser side
                    T num = FindMax(Left);
                    //Set this nodes value to that number
                    Value = num;
                    //Then delete the node we grabbed the value from
                    Left = Left.Remove(num);
                    //This is essentially moving the nodes around, but with much less effort and all the same benefits
                }

            }
            return BalanceTree();
        }

        public T FindMax(AVLTreeNode<T> startingNode)
        {
            //iterate through right nodes and return biggest
            AVLTreeNode<T> targetNode = startingNode;
            while (targetNode.Right != null)
            {
                targetNode = targetNode.Right;
                if (targetNode.Right == null)
                {
                    return targetNode.Value;
                }
            }
            return targetNode.Value;
        }

        public T FindMin(AVLTreeNode<T> startingNode)
        {
            //iterate through left nodes and return smallest
            AVLTreeNode<T> targetNode = startingNode;
            while (targetNode.Left != null)
            {
                targetNode = targetNode.Left;
                if (targetNode.Left == null)
                {
                    return targetNode.Value;
                }
            }
            return targetNode.Value;
        }

        public Boolean Contains(T value)
        {
            if (value.CompareTo(Value) < 0)
            {
                if (Left != null)
                {
                    return Left.Contains(value);
                }
            }
            else if (value.CompareTo(Value) > 0)
            {
                if (Right != null)
                {
                    return Right.Contains(value);
                }
            }
            else
            {
                return true;
            }
            return false;
        }

        public void InOrder(List<T> array)
        {
            if (Left != null)
            {
                Left.InOrder(array);
            }
            array.Add(this.Value);
            if (Right != null)
            {
                Right.InOrder(array);
            }
            return;
        }

        public void PostOrder(List<T> array)
        {
            if (Left != null)
            {
                Left.PostOrder(array);
            }
            if (Right != null)
            {
                Right.PostOrder(array);
            }
            array.Add(this.Value);
            return;
        }

        public void PreOrder(List<T> array)
        {
            array.Add(this.Value);
            if (Left != null)
            {
                Left.PreOrder(array);
            }
            if (Right != null)
            {
                Right.PreOrder(array);
            }
            return;
        }

        public int Height()
        {
            int left = 0;
            int right = 0;

            if (Left != null)
            {
                left = Left.Height();
            }

            if (Right != null)
            {
                right = Right.Height();
            }

            return 1 + Math.Max(left, right);
        }

        public AVLTreeNode<T> BalanceTree()
        {
            int leftHeight = 0; 
            if (Left != null)
            {
                leftHeight = Left.Height();
            }
            int rightHeight = 0;
            if (Right != null)
            {
                rightHeight = Right.Height();
            }
            //Console.WriteLine(leftHeight + " - " + rightHeight + " = " + (leftHeight - rightHeight));

            if (leftHeight - rightHeight > 1) //left heavier
            {
                // Do right rotation, possibly more
                int leftBF = (Left.Left != null ? Left.Left.Height() : 0) - (Left.Right != null ? Left.Right.Height() : 0);
                if (leftBF < 0)
                {
                    return LeftRightRotation();
                }
                else if (leftBF >= 0)
                {
                    return RightRotation();
                }

            } else if (leftHeight - rightHeight < -1) //right heavier
            {
                // Do left rotation, possibly more                
                int rightBF = (Right.Left != null ? Right.Left.Height() : 0) - (Right.Right != null ? Right.Right.Height() : 0);
                if (rightBF <= 0)
                {
                    return LeftRotation();
                } 
                else if (rightBF > 0)
                {
                    return RightLeftRotation();
                }
            }
            else
            {
                return this;
            }
            Console.WriteLine("How did I get here?");
            return this;
        }

        public AVLTreeNode<T> RightRotation()
        {
            AVLTreeNode<T> pivot = this.Left;
            AVLTreeNode<T> tempNode = pivot.Right;
            pivot.Right = this;
            this.Left = tempNode;
            return pivot;
        }

        public AVLTreeNode<T> LeftRotation()
        {
            AVLTreeNode<T> pivot = this.Right;
            AVLTreeNode<T> tempNode = pivot.Left;
            pivot.Left = this;
            this.Right = tempNode;
            return pivot;
        }

        public AVLTreeNode<T> LeftRightRotation()
        {
            Left = Left.LeftRotation();
            return RightRotation();
        }

        public AVLTreeNode<T> RightLeftRotation()
        {
            Right = Right.RightRotation();
            return LeftRotation();
        }

        public void ToArray(List<T> array, int index, int level)
        {
            if (level == 0)
            {
                array.Add(this.Value);
                return;
            }
            if (Left != null)
            {
                Left.ToArray(array, index, level - 1);
            }
            if (Right != null)
            {
                Right.ToArray(array, index, level - 1);
            }
        }
    }
}