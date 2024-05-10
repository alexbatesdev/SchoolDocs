using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW_follow_along
{
    internal class Dec_Encrypt : MyBase
    {
        MyBase _subClass = null;
        public Dec_Encrypt(MyBase subClass)
        {
            _subClass = subClass;
        }

        public override string WriteFile(string content)
        {
            //TODO: Return an encrypted version of 'content'
            string temp;
            // TODO write content
            if (_subClass != null)
                temp = _subClass.WriteFile(content);
            else
                temp = content;

            return temp;
        }
    }
}
