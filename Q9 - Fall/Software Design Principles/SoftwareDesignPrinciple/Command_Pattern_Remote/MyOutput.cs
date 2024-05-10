using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Diagnostics;

namespace Command_Pattern_Remote
{
    internal class MyOutput
    {
        // Create simple debugging tool
        public static void WriteLn(string str)
        {
            Debug.WriteLine(str);
        }
    }
}
