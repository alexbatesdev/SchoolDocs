using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Locations
{
    public class JustSayingHiException : Exception
    {
        public JustSayingHiException(string message) : base(message) { }
    }
}
