using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW_follow_along
{
    class Dec_MyWriteFile : MyBase
    {
        MyBase _subClass = null;

        public Dec_MyWriteFile(MyBase subClass)
        {
            _subClass = subClass;
        }

        public override string WriteFile(string content)
        {
            string temp;
            // TODO write content
            if (_subClass != null)
                temp = _subClass.WriteFile(content);
            else
                temp = content;

            //Write file
            Console.WriteLine(temp);

            return temp;
        }
    }
}
