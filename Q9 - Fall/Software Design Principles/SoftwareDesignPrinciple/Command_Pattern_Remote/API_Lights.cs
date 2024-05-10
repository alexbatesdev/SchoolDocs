using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    public class API_Lights
    {
        private string _light;
        public API_Lights(string letThereBe)
        {
            _light = letThereBe;
        }

        public void LightOn()
        {
            MyOutput.WriteLn("\n The " + _light + " lights are on");
        }

        public void LightOff()
        {
            MyOutput.WriteLn("\n The " + _light + " lights are off");
        }

        public string LightName()
        {
            return _light;
        }
    }
}
