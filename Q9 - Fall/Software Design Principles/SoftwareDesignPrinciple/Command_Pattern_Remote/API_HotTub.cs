using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    public class API_HotTub
    {
        public void BubblesOn()
        {
            MyOutput.WriteLn("\nHot Tub bubbles are on");
        }

        public void BubblesOff()
        {
            MyOutput.WriteLn("\nHot Tub bubbles are off");
        }
    }
}
