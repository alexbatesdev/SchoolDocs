namespace Command_Pattern_Remote
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.paneldepon = new System.Windows.Forms.Panel();
            this.Undo = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // paneldepon
            // 
            this.paneldepon.Location = new System.Drawing.Point(12, 12);
            this.paneldepon.Name = "paneldepon";
            this.paneldepon.Size = new System.Drawing.Size(612, 384);
            this.paneldepon.TabIndex = 0;
            // 
            // Undo
            // 
            this.Undo.Location = new System.Drawing.Point(12, 402);
            this.Undo.Name = "Undo";
            this.Undo.Size = new System.Drawing.Size(612, 23);
            this.Undo.TabIndex = 1;
            this.Undo.Text = "Undo";
            this.Undo.UseVisualStyleBackColor = true;
            this.Undo.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(636, 433);
            this.Controls.Add(this.Undo);
            this.Controls.Add(this.paneldepon);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel paneldepon;
        private System.Windows.Forms.Button Undo;
    }
}

