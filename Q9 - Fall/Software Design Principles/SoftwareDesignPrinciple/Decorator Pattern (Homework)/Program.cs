namespace Decorator_Pattern__Homework_
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //This was backwards before (FileBase being the last item instead of the base), I fixed it because it felt gross
            FileOperationAbstract file = new Dec_FileBase(new Dec_UpShift(new Dec_Signature(new Dec_LineEndingConverter(new Dec_DownShift(null)), "John")));
            file.SetFilePath("../../../file2.txt");
            file.WriteFile("Some\r\n text\r\n that needs shifting. HAL labs into IBM labs");

            /*
            If we swap the order of Signature and UpShift it will cause the signature to not get shifted
            because we are appending it after that operation.

            Pseudocode Example: 
                onlyShiftLetters = True

                file = FileBase(Signature(UpShift(null, onlyShiftLetters), "John"), "file.txt")
                file.WriteLine("IBM LABS HAL LABS")

                //Output = JCN MBCT IBM MBCT  - Kpio 10/3/2023
            Pseudocode Example 2:
                onlyShiftLetters = True

                file = FileBase(UpShift(Signature(null, "John"), onlyShiftLetters), "file2.txt")
                file.WriteLine("IBM LABS HAL LABS")
                
                //Output = JCN MBCT IBM MBCT  - John 10/3/2023

            //Pseudo code essentially just means python / lazy c# 
            */

            Console.WriteLine(file.ReadFile());
        }
    }
}