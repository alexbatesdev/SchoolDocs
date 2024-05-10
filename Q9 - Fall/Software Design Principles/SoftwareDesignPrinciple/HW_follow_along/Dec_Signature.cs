using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW_follow_along
{
    internal class Dec_Signature : MyBase
    {
        MyBase _subClass = null;
        string _signature = "";

        public Dec_Signature(MyBase subClass, string signature)
        {
            _signature = signature;
            _subClass = subClass;
        }

        public override string WriteFile(string content)
        {
            // TODO: Add a signature to 'content' and return
            //return content + _signature;

            string temp;

            if (_subClass != null)
                temp = _subClass.WriteFile(content);
            else
                temp = content;

            return temp + _signature;
        }
    }
}
