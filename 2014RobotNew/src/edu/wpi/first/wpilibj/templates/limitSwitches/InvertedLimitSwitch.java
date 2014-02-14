package edu.wpi.first.wpilibj.templates.limitSwitches;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.util.AllocationException;
import edu.wpi.first.wpilibj.util.CheckedAllocationException;

/**
 *
 * @author skodali
 */
public class InvertedLimitSwitch extends DigitalInput {

    public InvertedLimitSwitch(int moduleNumber, int channel) {
        super(moduleNumber, channel);
    }
    
    public InvertedLimitSwitch(int channel){
        super(channel);
    }
    
    public boolean get(){ 
        return !super.get(); 
    }

    public void requestInterrupts(Object handler, Object param){
        throw new Error("Not implemented, do not use!"); 
    }
    
    public void requestInterrupts(){
        throw new Error("Deprecated, do not use!");
    }
    
    public void requestInterrupts(boolean risingEdge, boolean fallingEdge){
        //COPIED CODE, DO NOT EDIT (copied from edu.wpi.first.wpilibj.DigitalInput.requestInterrupts)
        try {
            m_interruptIndex = interrupts.allocate();
        } catch (CheckedAllocationException e) {
            throw new AllocationException("No interrupts are left to be allocated");
        }

        allocateInterrupts(true);

        m_interrupt.writeConfig_Source_AnalogTrigger(getAnalogTriggerForRouting());
        m_interrupt.writeConfig_Source_Channel((byte) getChannelForRouting());
        m_interrupt.writeConfig_Source_Module((byte) getModuleForRouting());
        setUpSourceEdge(risingEdge, fallingEdge); 
    }
}
