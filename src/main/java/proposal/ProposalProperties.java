package proposal;

import static java.util.stream.Collectors.toUnmodifiableList;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

public class ProposalProperties {
    private final ProposalChildPojo proposalChildPojo;
    private final ProposalParentPojo proposalParentPojo;
    private final List<ProposalParentPojo> proposalParentPojoList;

    private ProposalProperties(
            ProposalChildPojo proposalChildPojo,
            ProposalParentPojo proposalParentPojo,
            List<ProposalParentPojo> proposalParentPojoList) {
        this.proposalChildPojo = proposalChildPojo;
        this.proposalParentPojo = proposalParentPojo;
        this.proposalParentPojoList = proposalParentPojoList;
    }

    public ProposalChildPojo getProposalChildPojo() {
        return proposalChildPojo;
    }

    public ProposalParentPojo getProposalParentPojo() {
        return proposalParentPojo;
    }

    public List<ProposalParentPojo> getProposalParentPojoList() {
        return proposalParentPojoList;
    }

    @ConfigurationProperties(prefix = "proposal")
    public static class Builder {
        private final ProposalChildPojo.Builder proposalChildPojoBuilder = ProposalChildPojo.newBuilder();
        private final ProposalParentPojo.Builder proposalParentPojoBuilder = ProposalParentPojo.newBuilder();
        private List<ProposalParentPojo.Builder> proposalParentPojoBuilderList;

        public ProposalProperties build() {
            return new ProposalProperties(
                    proposalChildPojoBuilder.build(),
                    proposalParentPojoBuilder.build(),
                    proposalParentPojoBuilderList.stream().map(ProposalParentPojo.Builder::build).collect(toUnmodifiableList()));
        }

        public ProposalChildPojo.Builder getProposalChildPojoBuilder() {
            return proposalChildPojoBuilder;
        }

        public ProposalParentPojo.Builder getProposalParentPojoBuilder() {
            return proposalParentPojoBuilder;
        }

        public void setProposalParentPojoBuilderList(List<ProposalParentPojo.Builder> proposalParentPojoBuilderList) {
            this.proposalParentPojoBuilderList = proposalParentPojoBuilderList;
        }
    }
}
