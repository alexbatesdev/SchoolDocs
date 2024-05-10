using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Factory_Pattern_Homework
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        public bool doExportXML;

        private void button2_Click(object sender, EventArgs e)
        {
            doExportXML = false;
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            doExportXML = true;
            this.Close();
        }
    }
}
