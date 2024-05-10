using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator_Pattern__Homework_
{
    internal class Dec_LineEndingConverter : FileOperationAbstract
    {
        public Dec_LineEndingConverter(FileOperationAbstract subOperation) : base(null)
        {
            _subOperation = subOperation;
        }

        public override string ReadFile()
        {
            if (_subOperation == null)
            {
                using (StreamReader reader = new StreamReader(_filepath))
                {
                    try
                    {
                        string content = reader.ReadToEnd();
                        return NewLineLogic(content);
                    }
                    catch (Exception e)
                    {
                        return e.Message;
                    }
                }
            }
            else
            {
                return NewLineLogic(_subOperation.ReadFile());
            }
        }

        private string NewLineLogic(string content)
        {
            StringBuilder builtString = new StringBuilder();
            for (int i = 0; i < content.Length; i++)
            {
                //I want to look for 2 characters, so I'm creating a temp string that is the current character and the next character
                string temp = "";
                if (i+1 < content.Length)
                {
                    temp = content[i].ToString() + content[i + 1].ToString();
                }

                if (temp == "\r\n")
                {
                    builtString.Append("\n");
                } 
                else
                {
                    builtString.Append(content[i]);
                }
            }


            return builtString.ToString();
        }
    }
}
