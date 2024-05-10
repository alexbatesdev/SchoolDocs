using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    internal class Dec_FileBase: FileOperationAbstract
    {
        public Dec_FileBase(FileOperationAbstract subOperation): base(null)
        {
            _subOperation = subOperation;
        }

        public override string WriteFile(string content)
        {
            string temp;
            //Go down the stack modifying the content with WriteFile
            if (_subOperation != null)
                temp = _subOperation.WriteFile(content);
            //Or do nothing with the content because we have no decorations
            else
                temp = content;

            //Now that we have gotten all of the modifications we can write the file
            using (StreamWriter writer = new StreamWriter(_filepath))
            {
                writer.WriteLine(temp);
            }

            //Personally I'd return void if I could, but we're locked into overriding a method that returns a string
            //In the future I will likely give the base its parent class
            return temp;
        }
    }
}
