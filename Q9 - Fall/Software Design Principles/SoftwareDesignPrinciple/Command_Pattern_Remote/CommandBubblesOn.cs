using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    public class CommandBubblesOn : ICommand
    {
        API_HotTub hotTub = new API_HotTub();

        public void execute()
        {
            hotTub.BubblesOn();
        }

        public string getDesc()
        {
            return "Turn Bubbles On";
        }

        public void undo()
        {
            hotTub.BubblesOff();
        }
    }
}
