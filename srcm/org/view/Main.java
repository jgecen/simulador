package org.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.parser.DeclareLabel;
import org.parser.Parser;
import org.parser.exception.ParserException;
import org.parser.exception.ScannerException;
import org.simalator.MemoryContent;
import org.simalator.Simulator;
import org.simalator.SimulatorExeption;

import javax.swing.JComboBox;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanelCenter = null;

	private JPanel jPanelWest = null;

	private JPanel jPanelEast = null;

	private JPanel jPanelCommands = null;

	private JPanel jPanelSrcAssembler = null;

	private JPanel jPanelSrc = null;

	private JPanel jPanelButtonCompiled = null;

	private JButton jButtonCompiled = null;

	private JSlider jSliderVelocity = null;

	private JButton jButtonOpen = null;

	private JButton jButtonStop = null;

	private JButton jButtonExec = null;

	private JButton jButtonStepByStep = null;

	private JPanel jPanelControlSignInput = null;

	private JPanel jPanelRegisters = null;

	private JButton jButtonReset = null;

	private JButton jButtonTrap = null;

	private JPanel jPanelControlSignOutput = null;

	private JPanel jPanelMemory = null;

	private JLabel jLabelR = null;

	private JLabel jLabelW = null;

	private JPanel jPanelRegisterHead = null;

	private JPanel jPanelRegisterFoot = null;

	private JLabel jLabelB = null;

	private JTextField jTextFieldB = null;

	private JLabel jLabelC = null;

	private JTextField jTextFieldC = null;

	private JLabel jLabelD = null;

	private JTextField jTextFieldD = null;

	private JTextField jTextFieldE = null;

	private JLabel JLabelE = null;

	private JLabel jLabelH = null;

	private JTextField jTextFieldH = null;

	private JLabel jLabelL = null;

	private JTextField jTextFieldL = null;

	private JLabel jLabelPC = null;

	private JTextField jTextFieldPC = null;

	private JLabel jLabelRI = null;

	private JTextField jTextFieldRI = null;

	private JLabel jLabelSP = null;

	private JTextField jTextFieldSP = null;

	private JLabel jLabelAcumulator = null;

	private JTextField jTextFieldAcumulator = null;

	private JLabel jLabelSF = null;

	private JScrollPane jScrollPaneSrc = null;

	private JTextArea jTextAreaSrc = null;

	private JScrollPane jScrollPaneMemory = null;

	private JTable jTableMemory = null;

	private Simulator simulator = null;

	private ObserverMemory observerMemory = null;

	private JTextField jTextFieldSF = null;

	private JLabel jLabelZF = null;

	private JTextField jTextFieldZF = null;

	private JLabel jLabelAC = null;

	private JTextField jTextFieldAC = null;

	private JLabel jLabelPF = null;

	private JTextField jTextFieldPF = null;

	private JLabel jLabelCF = null;

	private JTextField jTextFieldCF = null;

	private Parser parser = null;

	private JPanel jPanelQueue = null;

	private JScrollPane jScrollPanePilha = null;

	private JTextArea jTextAreaPilha = null;

	private JComboBox jComboBoxInt = null;

	/**
	 * This is the default constructor
	 */
	public Main() {
		super();
		initialize();
		this.setResizable(false);
		simulator = new Simulator();
		this.observerMemory = new ObserverMemory(this.jTableMemory);

		simulator.getMemory().addObserver(this.observerMemory);
		

		ObserverBus observerBus = new ObserverBus(this.jLabelR, this.jLabelW,
				this.jTextFieldRI);

		simulator.getBus().addObserver(observerBus);

		ObserverRegister16 observerRegister16A = new ObserverRegister16(
				jTextFieldAcumulator);
		simulator.getRegister16A().addObserver(observerRegister16A);

		ObserverRegister16 observerRegister16PC = new ObserverRegister16(
				jTextFieldPC);
		simulator.getRegister16PC().addObserver(observerRegister16PC);
		
		ObserverRegister16 observerRegister16SP = new ObserverRegister16(
				jTextFieldSP);
		simulator.getRegister16SP().addObserver(observerRegister16SP);
		
		ObserverStackPointer observerStackPointer = new ObserverStackPointer(jTextAreaPilha, this.simulator);
		simulator.getRegister16SP().addObserver(observerStackPointer);
		
		
		ObserverRegister8 observerRegister8RI = new ObserverRegister8(
				jTextFieldRI);
		simulator.getRegister8RI().addObserver(observerRegister8RI);

		ObserverRegister8 observerRegister8B = new ObserverRegister8(
				jTextFieldB);
		simulator.getRegister8B().addObserver(observerRegister8B);

		ObserverRegister8 observerRegister8C = new ObserverRegister8(
				jTextFieldC);
		simulator.getRegister8C().addObserver(observerRegister8C);

		ObserverRegister8 observerRegister8D = new ObserverRegister8(
				jTextFieldD);
		simulator.getRegister8D().addObserver(observerRegister8D);

		ObserverRegister8 observerRegister8E = new ObserverRegister8(
				jTextFieldE);
		simulator.getRegister8E().addObserver(observerRegister8E);

		ObserverRegister8 observerRegister8H = new ObserverRegister8(
				jTextFieldH);
		simulator.getRegister8H().addObserver(observerRegister8H);

		ObserverRegister8 observerRegister8L = new ObserverRegister8(
				jTextFieldL);
		simulator.getRegister8L().addObserver(observerRegister8L);

		ObserverFlagRegister observerFlagRegister = new ObserverFlagRegister(jPanelRegisterFoot,
				jTextFieldSF, jTextFieldZF, jTextFieldAC, jTextFieldPF,
				jTextFieldCF);
		simulator.getFlagRegister().addObserver(observerFlagRegister);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("Simulador");
		this.setBounds(new Rectangle(250, 50, 789, 600));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanelWest(), BorderLayout.WEST);
			jContentPane.add(getJPanelEast(), BorderLayout.EAST);
			jContentPane.add(getJPanelCenter(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelCenter
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCenter() {
		if (jPanelCenter == null) {
			jPanelCenter = new JPanel();
			jPanelCenter.setLayout(new BorderLayout());
			jPanelCenter.setPreferredSize(new Dimension(100, 100));
			jPanelCenter.setName("Test");
			jPanelCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jPanelCenter.setToolTipText("");
			jPanelCenter.add(getJPanelControlSignInput(), BorderLayout.NORTH);
			jPanelCenter.add(getJPanelRegisters(), BorderLayout.CENTER);
		}
		return jPanelCenter;
	}

	/**
	 * This method initializes jPanelWest
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelWest() {
		if (jPanelWest == null) {
			jPanelWest = new JPanel();
			jPanelWest.setLayout(new BorderLayout());
			jPanelWest.setPreferredSize(new Dimension(200, 50));
			jPanelWest.add(getJPanelCommands(), BorderLayout.NORTH);
			jPanelWest.add(getJPanelSrcAssembler(), BorderLayout.CENTER);
		}
		return jPanelWest;
	}

	/**
	 * This method initializes jPanelEast
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelEast() {
		if (jPanelEast == null) {
			jPanelEast = new JPanel();
			jPanelEast.setLayout(new BorderLayout());
			jPanelEast.setPreferredSize(new Dimension(300, 50));
			jPanelEast.add(getJPanelControlSignOutput(), BorderLayout.NORTH);
			jPanelEast.add(getJPanelMemory(), BorderLayout.CENTER);
			jPanelEast.add(getJPanelQueue(), BorderLayout.SOUTH);
		}
		return jPanelEast;
	}

	/**
	 * This method initializes jPanelCommands
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCommands() {
		if (jPanelCommands == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			flowLayout.setVgap(5);
			jPanelCommands = new JPanel();
			jPanelCommands.setBorder(BorderFactory.createTitledBorder(null,
					"Opções", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelCommands.setLayout(flowLayout);
			jPanelCommands.setPreferredSize(new Dimension(0, 100));
			jPanelCommands.add(getJButtonOpen(), null);
			jPanelCommands.add(getJButtonStop(), null);
			jPanelCommands.add(getJButtonExec(), null);
			jPanelCommands.add(getJButtonStepByStep(), null);
			jPanelCommands.add(getJSliderVelocity(), null);
		}
		return jPanelCommands;
	}

	/**
	 * This method initializes jPanelSrcAssembler
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelSrcAssembler() {
		if (jPanelSrcAssembler == null) {
			jPanelSrcAssembler = new JPanel();
			jPanelSrcAssembler.setLayout(new BorderLayout());
			jPanelSrcAssembler.add(getJPanelButtonCompiled(),
					BorderLayout.SOUTH);
			jPanelSrcAssembler.add(getJPanelSrc(), BorderLayout.CENTER);
		}
		return jPanelSrcAssembler;
	}

	/**
	 * This method initializes jPanelSrc
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelSrc() {
		if (jPanelSrc == null) {
			jPanelSrc = new JPanel();
			jPanelSrc.setLayout(new BorderLayout());
			jPanelSrc.setBorder(BorderFactory.createTitledBorder(null,
					"Código Assembly", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelSrc.add(getJScrollPaneSrc(), BorderLayout.CENTER);
		}
		return jPanelSrc;
	}

	/**
	 * This method initializes jPanelButtonCompiled
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelButtonCompiled() {
		if (jPanelButtonCompiled == null) {
			jPanelButtonCompiled = new JPanel();
			jPanelButtonCompiled.setLayout(new FlowLayout());
			jPanelButtonCompiled.setPreferredSize(new Dimension(0, 30));
			jPanelButtonCompiled.add(getJButtonCompiled(), null);
		}
		return jPanelButtonCompiled;
	}

	/**
	 * This method initializes jButtonCompiled
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCompiled() {
		if (jButtonCompiled == null) {
			jButtonCompiled = new JButton();
			jButtonCompiled.setPreferredSize(new Dimension(100, 20));
			jButtonCompiled.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonCompiled.setText("Compile");
			jButtonCompiled
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							String program = getJTextAreaSrc().getText();

							parser = new Parser();
							try {
								LinkedList<MemoryContent> address = parser
										.parse(program);
								simulator.getMemory().setAddress(address);
								
								Map<String,DeclareLabel> mapDeclareLabel = parser.getMapDeclareLabel();
								Collection<DeclareLabel> coll = mapDeclareLabel.values();
								jComboBoxInt.removeAll();
								for (DeclareLabel label : coll) {
									jComboBoxInt.addItem(label);
								}
								
							} catch (ScannerException e1) {
								JOptionPane.showMessageDialog(null, e1
										.getMessage());
							} catch (ParserException e1) {
								JOptionPane.showMessageDialog(null, e1
										.getMessage());
							}
						}
					});
		}
		return jButtonCompiled;
	}

	/**
	 * This method initializes jSliderVelocity
	 * 
	 * @return javax.swing.JSlider
	 */
	private JSlider getJSliderVelocity() {
		if (jSliderVelocity == null) {
			jSliderVelocity = new JSlider();
			jSliderVelocity.setMaximum(100);
			jSliderVelocity.setExtent(0);
			jSliderVelocity.setPreferredSize(new Dimension(170, 16));
			jSliderVelocity.setInverted(false);
			jSliderVelocity.setPaintLabels(true);
			jSliderVelocity
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							simulator.setSleepValue(jSliderVelocity.getValue());

						}
					});
		}
		return jSliderVelocity;
	}

	/**
	 * This method initializes jButtonOpen
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOpen() {
		if (jButtonOpen == null) {
			jButtonOpen = new JButton();
			jButtonOpen.setPreferredSize(new Dimension(70, 20));
			jButtonOpen.setText("Abrir");
		}
		return jButtonOpen;
	}

	/**
	 * This method initializes jButtonStop
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStop() {
		if (jButtonStop == null) {
			jButtonStop = new JButton();
			jButtonStop.setPreferredSize(new Dimension(70, 20));
			jButtonStop.setText("Stop");
			jButtonStop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					simulator.stopSimulation();
				}
			});
		}
		return jButtonStop;
	}
   
	/**
	 * This method initializes jButtonExec
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonExec() {
		if (jButtonExec == null) {
			jButtonExec = new JButton();
			jButtonExec.setPreferredSize(new Dimension(70, 20));
			jButtonExec.setText("Run");
			jButtonExec.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					jButtonExec.setEnabled(false);
					jButtonStepByStep.setEnabled(false);
						Thread t = new Thread() {
							public void run() {
								try {
									simulator.simulate();
								} catch (SimulatorExeption e) {
									JOptionPane.showMessageDialog(null, e
											.getMessage());

								}
								jButtonExec.setEnabled(true);
								jButtonStepByStep.setEnabled(true);
								return;
							}
						};
						t.start();
					return;
				}
			});
		}
		return jButtonExec;
	}

	/**
	 * This method initializes jButtonStepByStep
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStepByStep() {
		if (jButtonStepByStep == null) {
			jButtonStepByStep = new JButton();
			jButtonStepByStep.setPreferredSize(new Dimension(70, 20));
			jButtonStepByStep.setText("Step");
			jButtonStepByStep.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					jButtonStepByStep.setEnabled(false);
					jButtonExec.setEnabled(false);
							Thread t = new Thread() {
								public void run() {
									try {
										simulator.simulate();
									} catch (SimulatorExeption e) {
										JOptionPane.showMessageDialog(null, e
												.getMessage());
									}
									jButtonStepByStep.setEnabled(true);
									jButtonExec.setEnabled(true);
									return;
								}
							};
							t.start();
							try {
								Thread.sleep(5);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							simulator.stopSimulation();
					
				}
			});
		}
		return jButtonStepByStep;
	}

	/**
	 * This method initializes jPanelControlSignInput
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelControlSignInput() {
		if (jPanelControlSignInput == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setVgap(10);
			flowLayout1.setHgap(25);
			jPanelControlSignInput = new JPanel();
			jPanelControlSignInput.setBorder(BorderFactory.createTitledBorder(
					null, "Input", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelControlSignInput.setLayout(flowLayout1);
			jPanelControlSignInput.setPreferredSize(new Dimension(0, 100));
			jPanelControlSignInput.add(getJButtonReset(), null);
			jPanelControlSignInput.add(getJButtonTrap(), null);
			jPanelControlSignInput.add(getJComboBoxInt(), null);
		}
		return jPanelControlSignInput;
	}

	/**
	 * This method initializes jPanelRegisters
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelRegisters() {
		if (jPanelRegisters == null) {
			jPanelRegisters = new JPanel();
			jPanelRegisters.setLayout(new BorderLayout());
			jPanelRegisters.setBorder(BorderFactory.createTitledBorder(null,
					"Registradores", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelRegisters.add(getJPanelRegisterHead(), BorderLayout.NORTH);
			jPanelRegisters.add(getJPanelRegisterFoot(), BorderLayout.CENTER);
		}
		return jPanelRegisters;
	}

	/**
	 * This method initializes jButtonReset
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonReset() {
		if (jButtonReset == null) {
			jButtonReset = new JButton();
			jButtonReset.setPreferredSize(new Dimension(160, 20));
			jButtonReset.setText("Reset");
			jButtonReset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					simulator.getRegister16PC().setValue((short)0);
				}
			});
		}
		return jButtonReset;
	}

	/**
	 * This method initializes jButtonTrap
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonTrap() {
		if (jButtonTrap == null) {
			jButtonTrap = new JButton();
			jButtonTrap.setPreferredSize(new Dimension(80, 20));
			jButtonTrap.setText("Int");
			jButtonTrap.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jComboBoxInt.getItemCount() > 0){
						DeclareLabel dl = (DeclareLabel) jComboBoxInt.getSelectedItem();
						simulator.setAddresTrap(dl.getPosMemory());
						simulator.setTrap(true);
					}
				}
			});
		}
		return jButtonTrap;
	}

	/**
	 * This method initializes jPanelControlSignOutput
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelControlSignOutput() {
		if (jPanelControlSignOutput == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setHgap(25);
			flowLayout2.setVgap(15);
			jLabelW = new JLabel();
			jLabelW.setText("Write");
			jLabelW.setForeground(Color.black);
			jLabelW.setFont(new Font("Dialog", Font.BOLD, 24));
			jLabelR = new JLabel();
			jLabelR.setText("Read");
			jLabelR.setBackground(new Color(238, 238, 238));
			jLabelR.setForeground(Color.black);
			jLabelR.setFont(new Font("Dialog", Font.BOLD, 24));
			jPanelControlSignOutput = new JPanel();
			jPanelControlSignOutput.setLayout(flowLayout2);
			jPanelControlSignOutput.setPreferredSize(new Dimension(0, 100));
			jPanelControlSignOutput.setBorder(BorderFactory.createTitledBorder(
					null, "Output", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelControlSignOutput.add(jLabelR, null);
			jPanelControlSignOutput.add(jLabelW, null);
		}
		return jPanelControlSignOutput;
	}

	/**
	 * This method initializes jPanelMemory
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelMemory() {
		if (jPanelMemory == null) {
			jPanelMemory = new JPanel();
			jPanelMemory.setLayout(new BorderLayout());
			jPanelMemory.setBorder(BorderFactory.createTitledBorder(null,
					"Memória", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelMemory.setPreferredSize(new Dimension(200, 200));
			jPanelMemory.add(getJScrollPaneMemory(), BorderLayout.CENTER);
		}
		return jPanelMemory;
	}

	/**
	 * This method initializes jPanelRegisterHead
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelRegisterHead() {
		if (jPanelRegisterHead == null) {
			jLabelAcumulator = new JLabel();
			jLabelAcumulator.setText("Acumulador");
			jLabelSP = new JLabel();
			jLabelSP.setPreferredSize(new Dimension(70, 16));
			jLabelSP.setText("SP");
			jLabelSP.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelRI = new JLabel();
			jLabelRI.setPreferredSize(new Dimension(70, 16));
			jLabelRI.setText("RI");
			jLabelRI.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelPC = new JLabel();
			jLabelPC.setPreferredSize(new Dimension(70, 16));
			jLabelPC.setText("PC");
			jLabelPC.setBackground(new Color(238, 238, 238));
			jLabelPC.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelL = new JLabel();
			jLabelL.setPreferredSize(new Dimension(20, 16));
			jLabelL.setText("L");
			jLabelL.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelH = new JLabel();
			jLabelH.setPreferredSize(new Dimension(20, 16));
			jLabelH.setText("H");
			jLabelH.setHorizontalAlignment(SwingConstants.RIGHT);
			FlowLayout flowLayout3 = new FlowLayout();
			flowLayout3.setHgap(12);
			flowLayout3.setVgap(10);
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 8;
			gridBagConstraints9.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints9.gridy = 2;
			JLabelE = new JLabel();
			JLabelE.setText("E");
			JLabelE.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabelE.setPreferredSize(new Dimension(20, 16));
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.gridy = 2;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints6.gridx = 9;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints4.gridx = 5;
			jLabelD = new JLabel();
			jLabelD.setText("D");
			jLabelD.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelD.setPreferredSize(new Dimension(20, 16));
			jLabelC = new JLabel();
			jLabelC.setText("C");
			jLabelC.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelC.setPreferredSize(new Dimension(20, 16));
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 4;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.gridy = 1;
			jLabelB = new JLabel();
			jLabelB.setText("B");
			jLabelB.setHorizontalTextPosition(SwingConstants.TRAILING);
			jLabelB.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelB.setPreferredSize(new Dimension(20, 16));
			jPanelRegisterHead = new JPanel();
			jPanelRegisterHead.setLayout(flowLayout3);
			jPanelRegisterHead.setPreferredSize(new Dimension(0, 260));
			jPanelRegisterHead.add(jLabelB, null);
			jPanelRegisterHead.add(getJTextFieldB(), null);
			jPanelRegisterHead.add(jLabelC, null);
			jPanelRegisterHead.add(getJTextFieldC(), null);
			jPanelRegisterHead.add(jLabelD, null);
			jPanelRegisterHead.add(getJTextFieldD(), null);
			jPanelRegisterHead.add(JLabelE, null);
			jPanelRegisterHead.add(getJTextFieldE(), null);
			jPanelRegisterHead.add(jLabelH, null);
			jPanelRegisterHead.add(getJTextFieldH(), null);
			jPanelRegisterHead.add(jLabelL, null);
			jPanelRegisterHead.add(getJTextFieldL(), null);
			jPanelRegisterHead.add(jLabelPC, null);
			jPanelRegisterHead.add(getJTextFieldPC(), null);
			jPanelRegisterHead.add(jLabelRI, null);
			jPanelRegisterHead.add(getJTextFieldRI(), null);
			jPanelRegisterHead.add(jLabelSP, null);
			jPanelRegisterHead.add(getJTextFieldSP(), null);
			jPanelRegisterHead.add(jLabelAcumulator, null);
			jPanelRegisterHead.add(getJTextFieldAcumulator(), null);
		}
		return jPanelRegisterHead;
	}

	/**
	 * This method initializes jPanelRegisterFoot
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelRegisterFoot() {
		if (jPanelRegisterFoot == null) {
			jLabelCF = new JLabel();
			jLabelCF.setPreferredSize(new Dimension(100, 16));
			jLabelCF.setText("Carry Flag");
			jLabelCF.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelPF = new JLabel();
			jLabelPF.setPreferredSize(new Dimension(100, 16));
			jLabelPF.setText("Parity Flag");
			jLabelPF.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelAC = new JLabel();
			jLabelAC.setPreferredSize(new Dimension(100, 16));
			jLabelAC.setText("Auxialiary Carry");
			jLabelAC.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelZF = new JLabel();
			jLabelZF.setPreferredSize(new Dimension(100, 16));
			jLabelZF.setText("Zero Flag");
			jLabelZF.setHorizontalAlignment(SwingConstants.RIGHT);
			FlowLayout flowLayout4 = new FlowLayout();
			flowLayout4.setHgap(20);
			flowLayout4.setAlignment(FlowLayout.LEFT);
			flowLayout4.setVgap(7);
			jLabelSF = new JLabel();
			jLabelSF.setText("Signal Flag");
			jLabelSF.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelSF.setPreferredSize(new Dimension(100, 16));
			jPanelRegisterFoot = new JPanel();
			jPanelRegisterFoot.setLayout(flowLayout4);
			jPanelRegisterFoot.setBorder(BorderFactory.createTitledBorder(null,
					"Flags", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.ITALIC, 12), new Color(51, 51, 51)));
			jPanelRegisterFoot.add(jLabelSF, null);
			jPanelRegisterFoot.add(getJTextFieldSF(), null);
			jPanelRegisterFoot.add(jLabelZF, null);
			jPanelRegisterFoot.add(getJTextFieldZF(), null);
			jPanelRegisterFoot.add(jLabelAC, null);
			jPanelRegisterFoot.add(getJTextFieldAC(), null);
			jPanelRegisterFoot.add(jLabelPF, null);
			jPanelRegisterFoot.add(getJTextFieldPF(), null);
			jPanelRegisterFoot.add(jLabelCF, null);
			jPanelRegisterFoot.add(getJTextFieldCF(), null);
		}
		return jPanelRegisterFoot;
	}

	/**
	 * This method initializes jTextFieldB
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldB() {
		if (jTextFieldB == null) {
			jTextFieldB = new JTextField();
			jTextFieldB.setPreferredSize(new Dimension(80, 25));
			jTextFieldB.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldB.setText("00");
			jTextFieldB.setBackground(Color.white);
		}
		return jTextFieldB;
	}

	/**
	 * This method initializes jTextFieldC
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldC() {
		if (jTextFieldC == null) {
			jTextFieldC = new JTextField();
			jTextFieldC.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldC.setText("00");
			jTextFieldC.setPreferredSize(new Dimension(80, 25));
		}
		return jTextFieldC;
	}

	/**
	 * This method initializes jTextFieldD
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldD() {
		if (jTextFieldD == null) {
			jTextFieldD = new JTextField();
			jTextFieldD.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldD.setText("00");
			jTextFieldD.setPreferredSize(new Dimension(80, 25));
		}
		return jTextFieldD;
	}

	/**
	 * This method initializes jTextFieldE
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldE() {
		if (jTextFieldE == null) {
			jTextFieldE = new JTextField();
			jTextFieldE.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldE.setText("00");
			jTextFieldE.setPreferredSize(new Dimension(80, 25));
		}
		return jTextFieldE;
	}

	/**
	 * This method initializes jTextFieldH
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldH() {
		if (jTextFieldH == null) {
			jTextFieldH = new JTextField();
			jTextFieldH.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldH.setText("00");
			jTextFieldH.setPreferredSize(new Dimension(80, 25));
		}
		return jTextFieldH;
	}

	/**
	 * This method initializes jTextFieldL
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldL() {
		if (jTextFieldL == null) {
			jTextFieldL = new JTextField();
			jTextFieldL.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldL.setText("00");
			jTextFieldL.setPreferredSize(new Dimension(80, 25));
		}
		return jTextFieldL;
	}

	/**
	 * This method initializes jTextFieldPC
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPC() {
		if (jTextFieldPC == null) {
			jTextFieldPC = new JTextField();
			jTextFieldPC.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldPC.setText("0000");
			jTextFieldPC.setPreferredSize(new Dimension(100, 25));
		}
		return jTextFieldPC;
	}

	/**
	 * This method initializes jTextFieldRI
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRI() {
		if (jTextFieldRI == null) {
			jTextFieldRI = new JTextField();
			jTextFieldRI.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldRI.setPreferredSize(new Dimension(100, 25));
		}
		return jTextFieldRI;
	}

	/**
	 * This method initializes jTextFieldSP
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSP() {
		if (jTextFieldSP == null) {
			jTextFieldSP = new JTextField();
			jTextFieldSP.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldSP.setText("FFFF");
			jTextFieldSP.setPreferredSize(new Dimension(100, 25));
		}
		return jTextFieldSP;
	}

	/**
	 * This method initializes jTextFieldAcumulator
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldAcumulator() {
		if (jTextFieldAcumulator == null) {
			jTextFieldAcumulator = new JTextField();
			jTextFieldAcumulator.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldAcumulator.setText("0000");
			jTextFieldAcumulator.setPreferredSize(new Dimension(100, 25));
		}
		return jTextFieldAcumulator;
	}

	/**
	 * This method initializes jScrollPaneSrc
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneSrc() {
		if (jScrollPaneSrc == null) {
			jScrollPaneSrc = new JScrollPane();
			jScrollPaneSrc
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPaneSrc
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPaneSrc.setViewportView(getJTextAreaSrc());
		}
		return jScrollPaneSrc;
	}

	/**
	 * This method initializes jTextAreaSrc
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextAreaSrc() {
		if (jTextAreaSrc == null) {
			jTextAreaSrc = new JTextArea();
			jTextAreaSrc.setFont(new Font("Courier New", Font.PLAIN, 14));
		}
		return jTextAreaSrc;
	}

	/**
	 * This method initializes jScrollPaneMemory
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneMemory() {
		if (jScrollPaneMemory == null) {
			jScrollPaneMemory = new JScrollPane();
			jScrollPaneMemory.setViewportView(getJTableMemory());
		}
		return jScrollPaneMemory;
	}

	/**
	 * This method initializes jTableMemory
	 * 
	 * @return javax.swing.JTable
	 */
	@SuppressWarnings("serial")
	private JTable getJTableMemory() {
		if (jTableMemory == null) {
			jTableMemory = new JTable();/* {
				public Component prepareRenderer(TableCellRenderer renderer,
						int rowIndex, int colIndex) {
					Component c = super.prepareRenderer(renderer, rowIndex,
							colIndex);
					if (colIndex % 2 == 0
							&& !isCellSelected(rowIndex, colIndex)) {
						c.setBackground(Color.red);
					} else {
						// If not shaded, match the table's background
						c.setBackground(getBackground());
					}
					return c;
				}
			};
			*/
			jTableMemory.setModel(new DefaultTableModel());
			jTableMemory.setBackground(Color.white);
			jTableMemory.setGridColor(SystemColor.activeCaption);
			jTableMemory.setRowHeight(16);
			jTableMemory.setOpaque(false);
			jTableMemory
					.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			jTableMemory.setFont(new Font("Courier New", Font.PLAIN, 12));
		}
		return jTableMemory;
	}

	/**
	 * This method initializes jTextFieldSF
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSF() {
		if (jTextFieldSF == null) {
			jTextFieldSF = new JTextField();
			jTextFieldSF.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldSF.setPreferredSize(new Dimension(40, 21));
			jTextFieldSF.setText("1");
		}
		return jTextFieldSF;
	}

	/**
	 * This method initializes jTextFieldZF
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldZF() {
		if (jTextFieldZF == null) {
			jTextFieldZF = new JTextField();
			jTextFieldZF.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldZF.setPreferredSize(new Dimension(40, 21));
			jTextFieldZF.setText("1");
		}
		return jTextFieldZF;
	}

	/**
	 * This method initializes jTextFieldAC
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldAC() {
		if (jTextFieldAC == null) {
			jTextFieldAC = new JTextField();
			jTextFieldAC.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldAC.setPreferredSize(new Dimension(40, 21));
			jTextFieldAC.setText("1");
		}
		return jTextFieldAC;
	}

	/**
	 * This method initializes jTextFieldPF
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPF() {
		if (jTextFieldPF == null) {
			jTextFieldPF = new JTextField();
			jTextFieldPF.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldPF.setPreferredSize(new Dimension(40, 21));
			jTextFieldPF.setText("1");
		}
		return jTextFieldPF;
	}

	/**
	 * This method initializes jTextFieldCF
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCF() {
		if (jTextFieldCF == null) {
			jTextFieldCF = new JTextField();
			jTextFieldCF.setBorder(BorderFactory.createLineBorder(
					SystemColor.activeCaptionBorder, 2));
			jTextFieldCF.setPreferredSize(new Dimension(40, 21));
			jTextFieldCF.setText("1");
		}
		return jTextFieldCF;
	}

	/**
	 * This method initializes jPanelQueue	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelQueue() {
		if (jPanelQueue == null) {
			jPanelQueue = new JPanel();
			jPanelQueue.setLayout(new FlowLayout());
			jPanelQueue.setPreferredSize(new Dimension(193, 193));
			jPanelQueue.setBorder(BorderFactory.createTitledBorder(null, "Stack Pointer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelQueue.add(getJScrollPanePilha(), null);
		}
		return jPanelQueue;
	}

	/**
	 * This method initializes jScrollPanePilha	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPanePilha() {
		if (jScrollPanePilha == null) {
			jScrollPanePilha = new JScrollPane();
			jScrollPanePilha.setPreferredSize(new Dimension(60, 140));
			jScrollPanePilha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPanePilha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPanePilha.setFont(new Font("Dialog", Font.BOLD, 14));
			jScrollPanePilha.setViewportView(getJTextAreaPilha());
		}
		return jScrollPanePilha;
	}

	/**
	 * This method initializes jTextAreaPilha	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaPilha() {
		if (jTextAreaPilha == null) {
			jTextAreaPilha = new JTextArea();
			jTextAreaPilha.setFont(new Font("Courier New", Font.BOLD, 18));
			jTextAreaPilha.setText("00");
		}
		return jTextAreaPilha;
	}

	/**
	 * This method initializes jComboBoxInt	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxInt() {
		if (jComboBoxInt == null) {
			jComboBoxInt = new JComboBox();
			jComboBoxInt.setPreferredSize(new Dimension(120, 20));
			jComboBoxInt.setFont(new Font("Cordia New", Font.PLAIN, 18));
		}
		return jComboBoxInt;
	}

	public static void main(String a[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		Main m = new Main();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);		
	}

} // @jve:decl-index=0:visual-constraint="10,10"
