using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    public class CommandBubblesOff : ICommand
    {
        API_HotTub hotTub = new API_HotTub();

        public void execute()
        {
            hotTub.BubblesOff();
        }

        public string getDesc()
        {
            return "Turn Bubbles Off";
        }

        public void undo()
        {
            hotTub.BubblesOn();
        }
    }
}
